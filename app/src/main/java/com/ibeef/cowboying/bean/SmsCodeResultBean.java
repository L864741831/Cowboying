package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 12:04
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SmsCodeResultBean {


    /**
     * code : 000000
     * message : “”
     * bizData : true
     */

    private String code;
    private String message;
    private String bizData;

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

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }
}
