package com.hy.dynamicdatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */
@ConfigurationProperties("zjeport")
public class ZJEportConfig implements Serializable {

    private String api_url;

    private String application_form_no;

    private String account_book_no;

    private String assure_company_code;

    private String our_send_code;

    private String our_aes_secret_key;

    private String our_rsa_public_key;

    private String our_rsa_private_key;

    private String eport_aes_secret_key;

    private String eport_rsa_public_key;

    private String our_company_code;

    private String our_company_name;

    private String wms_company_code;

    private String wms_company_name;

    private String logistics_yt_company_code;

    private String logistics_yt_company_name;

    private String logistics_sf_company_code;

    private String logistics_sf_company_name;

    private String logistics_ems_company_code;

    private String logistics_ems_company_name;

    private String logistics_ttk_company_code;

    private String logistics_ttk_company_name;

    private String pay_ali_company_code;

    private String pay_ali_company_name;

    private String pay_wx_company_code;

    private String pay_wx_company_name;

    private String customs_hangzhou;

    private String customs_zongshu;

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getApplication_form_no() {
        return application_form_no;
    }

    public void setApplication_form_no(String application_form_no) {
        this.application_form_no = application_form_no;
    }

    public String getAccount_book_no() {
        return account_book_no;
    }

    public void setAccount_book_no(String account_book_no) {
        this.account_book_no = account_book_no;
    }

    public String getAssure_company_code() {
        return assure_company_code;
    }

    public void setAssure_company_code(String assure_company_code) {
        this.assure_company_code = assure_company_code;
    }

    public String getOur_send_code() {
        return our_send_code;
    }

    public void setOur_send_code(String our_send_code) {
        this.our_send_code = our_send_code;
    }

    public String getOur_aes_secret_key() {
        return our_aes_secret_key;
    }

    public void setOur_aes_secret_key(String our_aes_secret_key) {
        this.our_aes_secret_key = our_aes_secret_key;
    }

    public String getOur_rsa_public_key() {
        return our_rsa_public_key;
    }

    public void setOur_rsa_public_key(String our_rsa_public_key) {
        this.our_rsa_public_key = our_rsa_public_key;
    }

    public String getOur_rsa_private_key() {
        return our_rsa_private_key;
    }

    public void setOur_rsa_private_key(String our_rsa_private_key) {
        this.our_rsa_private_key = our_rsa_private_key;
    }

    public String getEport_aes_secret_key() {
        return eport_aes_secret_key;
    }

    public void setEport_aes_secret_key(String eport_aes_secret_key) {
        this.eport_aes_secret_key = eport_aes_secret_key;
    }

    public String getEport_rsa_public_key() {
        return eport_rsa_public_key;
    }

    public void setEport_rsa_public_key(String eport_rsa_public_key) {
        this.eport_rsa_public_key = eport_rsa_public_key;
    }

    public String getOur_company_code() {
        return our_company_code;
    }

    public void setOur_company_code(String our_company_code) {
        this.our_company_code = our_company_code;
    }

    public String getOur_company_name() {
        return our_company_name;
    }

    public void setOur_company_name(String our_company_name) {
        this.our_company_name = our_company_name;
    }

    public String getWms_company_code() {
        return wms_company_code;
    }

    public void setWms_company_code(String wms_company_code) {
        this.wms_company_code = wms_company_code;
    }

    public String getWms_company_name() {
        return wms_company_name;
    }

    public void setWms_company_name(String wms_company_name) {
        this.wms_company_name = wms_company_name;
    }

    public String getLogistics_yt_company_code() {
        return logistics_yt_company_code;
    }

    public void setLogistics_yt_company_code(String logistics_yt_company_code) {
        this.logistics_yt_company_code = logistics_yt_company_code;
    }

    public String getLogistics_yt_company_name() {
        return logistics_yt_company_name;
    }

    public void setLogistics_yt_company_name(String logistics_yt_company_name) {
        this.logistics_yt_company_name = logistics_yt_company_name;
    }

    public String getLogistics_sf_company_code() {
        return logistics_sf_company_code;
    }

    public void setLogistics_sf_company_code(String logistics_sf_company_code) {
        this.logistics_sf_company_code = logistics_sf_company_code;
    }

    public String getLogistics_sf_company_name() {
        return logistics_sf_company_name;
    }

    public void setLogistics_sf_company_name(String logistics_sf_company_name) {
        this.logistics_sf_company_name = logistics_sf_company_name;
    }

    public String getLogistics_ems_company_code() {
        return logistics_ems_company_code;
    }

    public void setLogistics_ems_company_code(String logistics_ems_company_code) {
        this.logistics_ems_company_code = logistics_ems_company_code;
    }

    public String getLogistics_ems_company_name() {
        return logistics_ems_company_name;
    }

    public void setLogistics_ems_company_name(String logistics_ems_company_name) {
        this.logistics_ems_company_name = logistics_ems_company_name;
    }

    public String getLogistics_ttk_company_code() {
        return logistics_ttk_company_code;
    }

    public void setLogistics_ttk_company_code(String logistics_ttk_company_code) {
        this.logistics_ttk_company_code = logistics_ttk_company_code;
    }

    public String getLogistics_ttk_company_name() {
        return logistics_ttk_company_name;
    }

    public void setLogistics_ttk_company_name(String logistics_ttk_company_name) {
        this.logistics_ttk_company_name = logistics_ttk_company_name;
    }

    public String getPay_ali_company_code() {
        return pay_ali_company_code;
    }

    public void setPay_ali_company_code(String pay_ali_company_code) {
        this.pay_ali_company_code = pay_ali_company_code;
    }

    public String getPay_ali_company_name() {
        return pay_ali_company_name;
    }

    public void setPay_ali_company_name(String pay_ali_company_name) {
        this.pay_ali_company_name = pay_ali_company_name;
    }

    public String getPay_wx_company_code() {
        return pay_wx_company_code;
    }

    public void setPay_wx_company_code(String pay_wx_company_code) {
        this.pay_wx_company_code = pay_wx_company_code;
    }

    public String getPay_wx_company_name() {
        return pay_wx_company_name;
    }

    public void setPay_wx_company_name(String pay_wx_company_name) {
        this.pay_wx_company_name = pay_wx_company_name;
    }

    public String getCustoms_hangzhou() {
        return customs_hangzhou;
    }

    public void setCustoms_hangzhou(String customs_hangzhou) {
        this.customs_hangzhou = customs_hangzhou;
    }

    public String getCustoms_zongshu() {
        return customs_zongshu;
    }

    public void setCustoms_zongshu(String customs_zongshu) {
        this.customs_zongshu = customs_zongshu;
    }
}