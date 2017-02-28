package com.hy.dynamicdatasource.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2017/2/9.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final String MASTER = "master";

    private static final String SLAVE = "slave";

    public static final String SEPARATOR = "_";

    private Map<Object, Object> dataSourceMap;

    private List<MasterSlaveDataSource> dataSourceList;

    private ThreadLocal<DataSourceKey> currentDataSource = new ThreadLocal<>();

    private ThreadLocal<Integer> currentDataSourceIndex = new ThreadLocal<>();

    private Map<String, DataSourceBalance> dataSourceBalanceMap = new HashMap<>();

    private List<DataSourceRoutePattern> masterPatterns = new ArrayList<>();

    private List<DataSourceRoutePattern> slavePatterns = new ArrayList<>();

    public void setCurrentDataSource(DataSourceKey key) {
        this.currentDataSource.set(key);
    }

    public void setCurrentDataSource(DataSourceKey key, int index) {
        this.currentDataSource.set(key);
        this.currentDataSourceIndex.set(index);
    }

    public Map<Object, Object> getDataSourceMap() {
        return this.dataSourceMap;
    }

    public Object routeDataSourceByJoinpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class claz = method.getDeclaringClass();
        com.hy.dynamicdatasource.annotation.DataSourceKey dataSourceKey = method.getAnnotation(com.hy.dynamicdatasource.annotation.DataSourceKey.class);
        if (dataSourceKey != null) {
            this.currentDataSource.set(DataSourceKey.createInstance(dataSourceKey.dataSourceName(), dataSourceKey.master()));
            this.currentDataSourceIndex.set(dataSourceKey.index());
        } else {
            for (DataSourceRoutePattern dataSourceRoutePattern : this.masterPatterns) {
                if (dataSourceRoutePattern.getAspectJExpressionPointcut().matches(method, claz)) {
                    this.currentDataSource.set(dataSourceRoutePattern.getDataSourceKey());
                    return joinPoint.proceed(joinPoint.getArgs());
                }
            }
            for (DataSourceRoutePattern dataSourceRoutePattern : this.slavePatterns) {
                if (dataSourceRoutePattern.getAspectJExpressionPointcut().matches(method, claz)) {
                    this.currentDataSource.set(dataSourceRoutePattern.getDataSourceKey());
                    return joinPoint.proceed(joinPoint.getArgs());
                }
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    public List<MasterSlaveDataSource> getDataSourceList() {
        return dataSourceList;
    }

    public void setDataSourceList(List<MasterSlaveDataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }

    public static DynamicDataSource createInstance(Class<? extends DataSource> claz) {
        DynamicDataSource.initEnvironment(claz);
        return new DynamicDataSource();
    }

    @Override
    public Object determineCurrentLookupKey() {
        try {
            DataSourceKey curDataSource = this.currentDataSource.get();
            Integer index = this.currentDataSourceIndex.get();
            if (index == null || index == 0) {
                index = this.dataSourceBalanceMap.get(curDataSource.getDataSourceFullName()).getCurrentDataSourceIndex();
            }
            return curDataSource.getDataSourceFullName(index);
        } catch (Exception e) {
            return "db_test_master_1";
        }
    }


    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        if (this.dataSourceList != null && !this.dataSourceList.isEmpty()) {
            for (MasterSlaveDataSource masterSlaveDataSource : this.dataSourceList) {
                String name = masterSlaveDataSource.getName();

                List<String> masterPatterns = masterSlaveDataSource.getMasterPatterns();
                if (masterPatterns != null && !masterPatterns.isEmpty()) {
                    for (String pattern : masterPatterns) {
                        this.masterPatterns.add(DataSourceRoutePattern.createInstance(
                                name,
                                true,
                                pattern
                        ));
                    }
                }

                List<String> slavePatterns = masterSlaveDataSource.getSlavePatterns();
                if (slavePatterns != null && !slavePatterns.isEmpty()) {
                    for (String pattern : slavePatterns) {
                        this.slavePatterns.add(DataSourceRoutePattern.createInstance(
                                name,
                                false,
                                pattern
                        ));
                    }
                }

                List<WeightDataSource> masters = masterSlaveDataSource.getMasters();
                if (masters != null && !masters.isEmpty()) {
                    DataSourceKey key = DataSourceKey.createInstance(name, true);
                    this.dataSourceBalanceMap.put(
                            key.getDataSourceFullName(),
                            new DataSourceBalance(masters)
                    );
                    for (WeightDataSource master : masters) {
                        targetDataSource.put(key.getDataSourceFullName(master.getIndex()), master.getDataSource());
                    }
                }
                List<WeightDataSource> slaves = masterSlaveDataSource.getSalves();
                if (slaves != null && !slaves.isEmpty()) {
                    DataSourceKey key = DataSourceKey.createInstance(name, false);
                    this.dataSourceBalanceMap.put(
                            key.getDataSourceFullName(),
                            new DataSourceBalance(slaves)
                    );
                    for (WeightDataSource slave : slaves) {
                        targetDataSource.put(key.getDataSourceFullName(slave.getIndex()), slave.getDataSource());
                    }
                }
            }
        }
        if (!targetDataSource.isEmpty()) {
            this.dataSourceMap = targetDataSource;
            this.setTargetDataSources(targetDataSource);
        } else {
            throw new IllegalArgumentException("\'route-data-source\' configuration error");
        }
        super.afterPropertiesSet();
    }

    protected static Class<? extends DataSource> dataSourceClass;

    public static void initEnvironment(Class<? extends DataSource> dataSourceClass) {
        DynamicDataSource.dataSourceClass = dataSourceClass;
    }

    private class DataSourceBalance {

        private int totalWeight = 0;

        List<WeightDataSource> weightDataSourceList;

        public DataSourceBalance(List<WeightDataSource> weightDataSources) {
            this.totalWeight = 0;
            this.weightDataSourceList = weightDataSources;
        }

        public int getCurrentDataSourceIndex() {
            if (weightDataSourceList != null && !weightDataSourceList.isEmpty()) {
                this.totalWeight++;
                int weight = 0;
                for (WeightDataSource dataSource: weightDataSourceList) {
                    weight += dataSource.getWeight();
                    if (weight >= this.totalWeight) {
                        return dataSource.getIndex();
                    }
                }
                this.totalWeight = 0;
                return weightDataSourceList.get(0).getIndex();
            } else {
                return 0;
            }
        }
    }

    private static class DataSourceKey {

        private final String dataSourceName;

        private final boolean master;

        private DataSourceKey(String dataSourceName, boolean master) {
            this.dataSourceName = dataSourceName;
            this.master = master;
        }

        public String getDataSourceName() {
            return dataSourceName;
        }

        public boolean isMaster() {
            return master;
        }

        public String getDataSourceFullName() {
            return this.dataSourceName + DynamicDataSource.SEPARATOR +
                    (this.master ? DynamicDataSource.MASTER : DynamicDataSource.SLAVE);
        }

        public String getDataSourceFullName(int index) {
            return this.getDataSourceFullName() + DynamicDataSource.SEPARATOR + index;
        }

        public static DataSourceKey createInstance(String dataSourceName, boolean master) {
            return new DataSourceKey(dataSourceName, master);
        }

    }

    private static class DataSourceRoutePattern {

        private final DataSourceKey dataSourceKey;

        private final AspectJExpressionPointcut aspectJExpressionPointcut;

        private DataSourceRoutePattern (String dataSourceName, boolean master, String expression) {
            this.dataSourceKey = DataSourceKey.createInstance(dataSourceName, master);
            AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
            aspectJExpressionPointcut.setExpression(expression);
            this.aspectJExpressionPointcut = aspectJExpressionPointcut;
        }

        public DataSourceKey getDataSourceKey() {
            return dataSourceKey;
        }

        public AspectJExpressionPointcut getAspectJExpressionPointcut() {
            return aspectJExpressionPointcut;
        }

        public static DataSourceRoutePattern createInstance(String dataSourceName, boolean master, String expression) {
            return new DataSourceRoutePattern(dataSourceName, master, expression);
        }

    }

}
