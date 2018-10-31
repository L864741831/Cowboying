package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 15:43
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ThirdCountLoginResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NDA5NzE4NzEsImxvZ2luVHlwZSI6IjQiLCJpc3MiOiJ3d3cueWFiZWkuc2hvcCIsImV4cCI6MTU0MzU2Mzg3MSwiaWF0IjoxNTQwOTcxODcxLCJ1c2VySWQiOjIyfQ.KTebhKhxVenc-rkNqd1qTLnhvgdbIQ-r6MOp6CwY_EE
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
