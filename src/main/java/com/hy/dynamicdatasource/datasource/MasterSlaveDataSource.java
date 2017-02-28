package com.hy.dynamicdatasource.datasource;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */
public class MasterSlaveDataSource {

    private String name;

    private List<String> masterPatterns;

    private List<WeightDataSource> masters;

    private List<String> slavePatterns;

    private List<WeightDataSource> salves;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMasterPatterns() {
        return masterPatterns;
    }

    public void setMasterPatterns(List<String> masterPatterns) {
        this.masterPatterns = masterPatterns;
    }

    public List<WeightDataSource> getMasters() {
        return masters;
    }

    public void setMasters(List<WeightDataSource> masters) {
        this.masters = masters;
    }

    public List<String> getSlavePatterns() {
        return slavePatterns;
    }

    public void setSlavePatterns(List<String> slavePatterns) {
        this.slavePatterns = slavePatterns;
    }

    public List<WeightDataSource> getSalves() {
        return salves;
    }

    public void setSalves(List<WeightDataSource> salves) {
        this.salves = salves;
    }
}
