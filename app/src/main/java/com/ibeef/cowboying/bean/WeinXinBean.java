package com.ibeef.cowboying.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @Author $ls
 * @Date 2018/4/7 13:53
 * @Description
 */

public class WeinXinBean {


    /**
     * appid : wx0678b96a189375f3
     * noncestr : f04d904da44c426f8fd68b18705d3962
     * package : Sign=WXPay
     * partnerid : 1488425672
     * prepayid : wx07135210349734ef294da0191715874870
     * timestamp : 1523080330
     * sign : 830D392A6870BA2FEE33FF06B1064571
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
