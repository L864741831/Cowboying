package com.ranhan.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/12 16:18
 * @describe
 * @package com.ranhan.yabei.bean
 **/
public class WeixinAuthFirstBean {


    /**
     * access_token : 14_wnywP6EbxQlUy1SMJaiUIFg3VM9p9l9Q50JkScL5XGezosOdbCuvjCXrYEqP_Ya6C3Jhr76ph0xmS43RUGlNqzPfZQAyjZFDNwwsRBlWneI
     * expires_in : 7200
     * refresh_token : 14_vCyuE_i2oP821s2mhfT_aXLDdwaz1RDJez1MwesFxQJXPN2MZ8M-3fKmDhUK1zGlX234Ce_Ht9mLKfCB_DevAablar3H4cMoYwpGbDq0Su0
     * openid : oBQcB0v4FD4JCT4BD_vRRBOgg2zU
     * scope : snsapi_userinfo
     * unionid : oGppJ0c5nIJIpE_spEMFETf-VF1Q
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
