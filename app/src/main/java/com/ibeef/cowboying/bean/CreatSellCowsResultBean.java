package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/15 10:37
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CreatSellCowsResultBean {

    /**
     * code : 000000
     * message :
     * bizData : null
     */

    private String code;
    private String message;
    private Object bizData;

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

    public Object getBizData() {
        return bizData;
    }

    public void setBizData(Object bizData) {
        this.bizData = bizData;
    }
}
