package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 16:11
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class NowPayOrderParamBean {


    private int addressId;
    private int couponId;
    private String receiveType;
    private String addressDetail;

    private List<AddStoreCarParamBean> productQuantityReqDtos;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public List<AddStoreCarParamBean> getProducts() {
        return productQuantityReqDtos;
    }

    public void setProducts(List<AddStoreCarParamBean> products) {
        this.productQuantityReqDtos = products;
    }
}
