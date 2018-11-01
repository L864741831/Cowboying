package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 13:56
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class BindThirdCountParamBean {
    private String type;
    private String authCode;
    private String openId;
    private String loginZone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLoginZone() {
        return loginZone;
    }

    public void setLoginZone(String loginZone) {
        this.loginZone = loginZone;
    }
}
