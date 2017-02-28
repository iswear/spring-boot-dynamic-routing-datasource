package com.hy.dynamicdatasource.datasource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */
public class WeightDataSource {

    private int weight;

    private int index;

    private DataSource dataSource;

    public WeightDataSource() throws IllegalAccessException, InstantiationException {
        this.dataSource = DynamicDataSource.dataSourceClass.newInstance();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
