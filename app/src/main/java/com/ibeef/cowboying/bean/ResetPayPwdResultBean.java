package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/13 15:45
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ResetPayPwdResultBean {
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
