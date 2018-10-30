package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 13:56
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class BindThirdCountParamBean {
    private String userId;
    private String type;
    private String accessToken;
    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
}
