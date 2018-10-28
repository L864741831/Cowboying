package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 12:06
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ValidateSmsCodeParamBean {
    private String smsCode;
    private String phone;
    private String smsType;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
}
