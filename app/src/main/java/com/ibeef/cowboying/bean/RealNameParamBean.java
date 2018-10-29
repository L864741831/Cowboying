package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 10:43
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class RealNameParamBean {
    private String userId;
    private String userMobile;
    private String realName;
    private String realCardNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealCardNo() {
        return realCardNo;
    }

    public void setRealCardNo(String realCardNo) {
        this.realCardNo = realCardNo;
    }
}
