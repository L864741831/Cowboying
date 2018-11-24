package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/28 11:14
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class AccountRegisterParamBean {

     private String mobile;
     private String code;
     private String machineCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
