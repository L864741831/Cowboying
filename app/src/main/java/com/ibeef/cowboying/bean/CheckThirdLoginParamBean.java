package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/19 14:28
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CheckThirdLoginParamBean {
    private String mobile;
    private String machineCode;
    private Integer visitorId;

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }
}
