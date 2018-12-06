package com.ibeef.cowboying.bean;

import java.io.Serializable;

/**
 * @author ls
 * @date on 2018/12/5 11:47
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class AddStoreCarParamBean implements Serializable {
    private int productId;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
