package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/6 14:08
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CouponNumParamBean {
    private String useType;
    private String schemeId;
    private String quantity;
    private Integer currentPage;
    private List<AddStoreCarParamBean> productQuantityReqDtos;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<AddStoreCarParamBean> getProductQuantityReqDtos() {
        return productQuantityReqDtos;
    }

    public void setProductQuantityReqDtos(List<AddStoreCarParamBean> productQuantityReqDtos) {
        this.productQuantityReqDtos = productQuantityReqDtos;
    }
}
