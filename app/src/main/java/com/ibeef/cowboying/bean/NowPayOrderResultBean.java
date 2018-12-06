package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/5 16:08
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class NowPayOrderResultBean {

    /**
     * code : 000000
     * message :
     * bizData : 14
     */

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
