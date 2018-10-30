package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/30 16:32
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ThirdLoginResultBean {

    /**
     * code : C1010105
     * message : 第三方登录失败
     * bizData :
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
