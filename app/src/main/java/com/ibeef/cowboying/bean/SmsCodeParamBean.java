package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 12:03
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SmsCodeParamBean {
    private String smsType;
    private String phone;
    private String uniqueCode;
    private String time;
    private String platform;
    private String sign;

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
