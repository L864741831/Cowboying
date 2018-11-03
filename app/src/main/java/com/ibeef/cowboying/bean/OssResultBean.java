package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/3 18:37
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class OssResultBean {

    /**
     * StatusCode : 200
     * AccessKeyId : STS.NJGvUMqtgD7G6ULrmw6ZLfynJ
     * AccessKeySecret : AVEf3Uq1ZfD2oLcndGgsA2q7dRFv68fvhNedfLYdgfdz
     * SecurityToken : CAISlQN1q6Ft5B2yfSjIr4nyPe/5nKtG8/WsNHP9lm0iOtVgibzFqDz2IHpJeHZtAO8cvvQwmGBX7vgelrh+W4NIX0rNaY5t9ZlN9wqkbtICUm0UaPhW5qe+EE2/VjRAta27OpdcJbGwU/OpbE++fEYRpZLxaTSlWXG8LJSNkuQJR98LXw6+HzgkYdBNPVlNpdM9P3ncPurPVxnxmTjoEVZPsAhxgn8dkaOk2Z+w5x7TkFDdwO0YrJiTR5+/dJtIPYxja96vwPcUBq3ay3x54R9Q6IJriLBF4T7douyFB15Y73fhNPHPoPluNxNebKo3ELJf1q+e88d1oevOjY/65g9QNOVOKUTlSZun3dHPFZHiVJ8wfq2pPG/WgJLddMvtuhg4fGgWcQhHfcYsMDhxDBUjRyr4Uvb8oQqaOlz5G/HZjPFnicZPog+2rYbQFT+mWK6E1CsUAJg4Yn4zOgQetW6bKfVbL1MRKgw9V+bKEdUsNE4Cs9DhuQzDSytn1WpLuP7zYfzbvK0FYJn4RIhB1YcNxCOucc79jT4agAGVn2dK/wMmQ33XGp+cM52oLzxav1HR6CzSyvRuJ3uA0qfgIrttrBWmzjKkY+O1oEZ9uWm01F25WYub/ll225Q2W8gJ//A6Ce8n6nVnoMXeNRr9G+FD7WKzc5VF7lvFcZ+UX8ppUpjioeH5FAhO6/KpfBSOOcev9wkgvcMlTMrIqQ==
     * Expiration : 2018-11-03T10:50:41Z
     */

    private String StatusCode;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String SecurityToken;
    private String Expiration;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String AccessKeyId) {
        this.AccessKeyId = AccessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String SecurityToken) {
        this.SecurityToken = SecurityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String Expiration) {
        this.Expiration = Expiration;
    }
}
