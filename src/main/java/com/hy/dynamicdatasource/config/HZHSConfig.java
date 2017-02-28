package com.hy.dynamicdatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/20.
 */
@ConfigurationProperties("hzhs")
public class HZHSConfig implements Serializable {

    private String api_url;

    private String wms_id;

    private String stock_id;

    private String owner_id;

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public void setWms_id(String wms_id) {
        this.wms_id = wms_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getApi_url() {
        return api_url;
    }

    public String getWms_id() {
        return wms_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public String getOwner_id() {
        return owner_id;
    }
}