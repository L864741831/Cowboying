package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 15:42
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ThirdCountLoginParamBean {
    private String type;
    private String accessToken;
    private String openId;
    private String loginZone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
