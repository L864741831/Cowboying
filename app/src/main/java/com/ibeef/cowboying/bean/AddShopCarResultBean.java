package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/6 19:56
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class AddShopCarResultBean {
    List<AddStoreCarParamBean> shopCartReqVos;

    public List<AddStoreCarParamBean> getShopCartReqVos() {
        return shopCartReqVos;
    }

    public void setShopCartReqVos(List<AddStoreCarParamBean> shopCartReqVos) {
        this.shopCartReqVos = shopCartReqVos;
    }
}
