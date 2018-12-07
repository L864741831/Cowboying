package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 16:11
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class NowPayOrderParamBean {


    private Integer addressId;
    private Integer couponId;
    private Integer storeId;
    private String receiveType;
    private String addressDetail;
    private List<AddStoreCarParamBean> productQuantityReqDtos;
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
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
