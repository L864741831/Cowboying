package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/17 16:00
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyContractURLBean {
    private String code;
    private String message;
    private BizDataBean bizData;

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

    public BizDataBean getBizData() {
        return bizData;
    }

    public void setBizData(BizDataBean bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {

    }
}
