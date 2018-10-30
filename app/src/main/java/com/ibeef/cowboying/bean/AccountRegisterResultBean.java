package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 11:14
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class AccountRegisterResultBean {

    /**
     * code :
     * message :
     * Data : {}
     */

    private String code;
    private String message;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public  class DataBean {
    }
}
