package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/19 14:28
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CheckThirdLoginResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NDI2MTEwMjgsImxvZ2luVHlwZSI6IjIiLCJpc3MiOiJ3d3cueWFiZWkuc2hvcCIsImV4cCI6MTU0NTIwMzAyOCwiaWF0IjoxNTQyNjExMDI4LCJ1c2VySWQiOjU1fQ.iKc0oUW3NIrKT0JNNX9YFwp-psewBaaLJOo13D6xHj0
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
