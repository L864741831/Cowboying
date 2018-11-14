package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/14 17:00
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PayInitParamBean {
    private Integer orderId;
    private String payType;
    private String secret;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
