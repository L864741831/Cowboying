package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/14 16:58
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CreatOrderParamBean {
    private Integer schemeId;
    private Integer quantity;
    private String recommender;

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }
}
