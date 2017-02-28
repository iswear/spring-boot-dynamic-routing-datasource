package com.hy.dynamicdatasource.entity.hzhs;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */
public class HZHSCallbackBody implements Serializable {

    private String sign;

    private Integer notify_type;

    private String notify_id;

    private String notify_time;

    private String wms_id;

    private String stock_id;

    private String owner_id;

    private String data;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(Integer notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getWms_id() {
        return wms_id;
    }

    public void setWms_id(String wms_id) {
        this.wms_id = wms_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
