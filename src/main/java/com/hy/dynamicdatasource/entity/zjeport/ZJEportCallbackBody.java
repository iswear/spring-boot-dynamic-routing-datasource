package com.hy.dynamicdatasource.entity.zjeport;

/**
 * Created by Administrator on 2017/1/20.
 */
public class ZJEportCallbackBody {

    private String content;

    private String msg_type;

    private String data_digest;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getData_digest() {
        return data_digest;
    }

    public void setData_digest(String data_digest) {
        this.data_digest = data_digest;
    }
}
