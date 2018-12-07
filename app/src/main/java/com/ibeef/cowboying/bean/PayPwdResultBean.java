package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/7 15:40
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PayPwdResultBean {

    /**
     * code : 000000
     * message :
     * bizData : true
     */

    private String code;
    private String message;
    private boolean bizData;

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

    public boolean isBizData() {
        return bizData;
    }

    public void setBizData(boolean bizData) {
        this.bizData = bizData;
    }
}
