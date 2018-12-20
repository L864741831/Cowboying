package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/10 20:25
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class StoreOneResultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"cartProductNum":3,"shopProductResVo":{"productId":35,"code":"201812050018","name":"啦啦啦啦啦啦啦啦啦啦啦","status":"1","categoryId":3,"specification":"500kg","price":0.5,"carriage":200000,"weight":null,"marketPrice":null,"stock":483,"saleNum":17,"describes":""},"productImages":[{"id":137,"productId":35,"imageUrl":"images/e71ff25f949b9d1c9d83f99e0ed99329","type":"1"},{"id":138,"productId":35,"imageUrl":"images/93107bc660844514e2392ea1db6af48d","type":"2"}],"categoryResVo":{"categoryId":3,"name":"牛腿肉","imageUrl":"images/b6373eef1e9345c68d4c78f150a70662.jpg","status":"1"},"productVideoResVos":[]}
     */

    private String code;
    private String message;
    private StoreInfoListResultBean.BizDataBean bizData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StoreInfoListResultBean.BizDataBean getBizData() {
        return bizData;
    }

    public void setBizData(StoreInfoListResultBean.BizDataBean bizData) {
        this.bizData = bizData;
    }
}
