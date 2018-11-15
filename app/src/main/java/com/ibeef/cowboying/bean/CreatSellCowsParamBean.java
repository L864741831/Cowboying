package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/15 10:35
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CreatSellCowsParamBean {
    private String orderId;
    private String sellType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }
}
