package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/7 14:04
 * @describe
 * @package com.ranhan.cowboying.bean
 **/
public class LoginBean {

    /**
     * code : 000000
     * message :
     * bizData : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NDA4ODIzMjQsImxvZ2luVHlwZSI6IjIiLCJpc3MiOiJ3d3cueWFiZWkuc2hvcCIsImV4cCI6MTU0MzQ3NDMyNCwiaWF0IjoxNTQwODgyMzI0LCJ1c2VySWQiOjl9.UOwm-jR5A1sqLYGzCt0ivWPzaYscxhw6lJt2mtWSaSQ
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
