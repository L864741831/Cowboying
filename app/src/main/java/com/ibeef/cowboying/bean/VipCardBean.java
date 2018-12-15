package com.ibeef.cowboying.bean;

public class VipCardBean {

    /**
     * code : 000000
     * message :
     * bizData : {"id":"","amount":1523.362,"cardNo":"","createTime":"","updateTime":"","storeAddress":"嘻嘻嘻"}
     */

    private String code;
    private String message;
    private BizDataBean bizData;

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

    public BizDataBean getBizData() {
        return bizData;
    }

    public void setBizData(BizDataBean bizData) {
        this.bizData = bizData;
    }

    public static class BizDataBean {
        /**
         * id :
         * amount : 1523.362
         * cardNo :
         * createTime :
         * updateTime :
         * storeAddress : 嘻嘻嘻
         */

        private String id;
        private double amount;
        private String cardNo;
        private String createTime;
        private String updateTime;
        private String storeAddress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }
    }
}
