package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/23 20:33
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CouponNumResultBean {
    private String code;
    private String message;
    private int bizData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBizData() {
        return bizData;
    }

    public void setBizData(int bizData) {
        this.bizData = bizData;
    }
}
