package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 10:34
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SubmitFeedbackResultBean {
    /**
     * code :
     * message :
     * data : {}
     */

    private String code;
    private String message;
    private MyFeedbackResultBean.BizDataBean data;

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

    public MyFeedbackResultBean.BizDataBean getData() {
        return data;
    }

    public void setData(MyFeedbackResultBean.BizDataBean data) {
        this.data = data;
    }

}
