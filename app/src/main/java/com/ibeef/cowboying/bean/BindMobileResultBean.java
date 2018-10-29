package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 11:17
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class BindMobileResultBean {

    /**
     * code :
     * message :
     * data : {}
     */

    private String code;
    private String message;
    private DataBean data;

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
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {
    }
}
