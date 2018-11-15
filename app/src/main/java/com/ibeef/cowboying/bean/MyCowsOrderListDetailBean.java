package com.ibeef.cowboying.bean;

import java.util.List;

public class MyCowsOrderListDetailBean {

    /**
     * code : 000000
     * message : “”
     * bizData : {"orderCode":"112335466422400","status":"2","cattleCount":1,"orderAmount":5000,"pastureName":"撒","pastureImage":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","cattleList":[{"cattleCode":"2222222","cattleImage":"images/ca09570497f14175b29cc0be930be12c.png","cattleWeight":100}],"adoptType":"1","unlockTime":"\u201c\u201d","lockMonths":3,"schemeCode":"11111111","price":5000,"payType":"1","createTime":"2018-11-14 10:14:11","payTime":"2018-11-14 14:38:44","distributeTime":""}
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

    public  class BizDataBean {
        /**
         * orderCode : 112335466422400
         * status : 2
         * cattleCount : 1
         * orderAmount : 5000.0
         * pastureName : 撒
         * pastureImage : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * cattleList : [{"cattleCode":"2222222","cattleImage":"images/ca09570497f14175b29cc0be930be12c.png","cattleWeight":100}]
         * adoptType : 1
         * unlockTime : “”
         * lockMonths : 3.0
         * schemeCode : 11111111
         * price : 5000
         * payType : 1
         * createTime : 2018-11-14 10:14:11
         * payTime : 2018-11-14 14:38:44
         * distributeTime :
         */

        private String orderCode;
        private String status;
        private int cattleCount;
        private double orderAmount;
        private String pastureName;
        private String pastureImage;
        private String adoptType;
        private String unlockTime;
        private double lockMonths;
        private String schemeCode;
        private double price;
        private String payType;
        private String createTime;
        private String payTime;
        private String distributeTime;
        private String sellTime;
        private String sellSuccessTime;
        private String closeTime;
        private List<CattleListBean> cattleList;

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getSellTime() {
            return sellTime;
        }

        public void setSellTime(String sellTime) {
            this.sellTime = sellTime;
        }

        public String getSellSuccessTime() {
            return sellSuccessTime;
        }

        public void setSellSuccessTime(String sellSuccessTime) {
            this.sellSuccessTime = sellSuccessTime;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCattleCount() {
            return cattleCount;
        }

        public void setCattleCount(int cattleCount) {
            this.cattleCount = cattleCount;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getPastureName() {
            return pastureName;
        }

        public void setPastureName(String pastureName) {
            this.pastureName = pastureName;
        }

        public String getPastureImage() {
            return pastureImage;
        }

        public void setPastureImage(String pastureImage) {
            this.pastureImage = pastureImage;
        }

        public String getAdoptType() {
            return adoptType;
        }

        public void setAdoptType(String adoptType) {
            this.adoptType = adoptType;
        }

        public String getUnlockTime() {
            return unlockTime;
        }

        public void setUnlockTime(String unlockTime) {
            this.unlockTime = unlockTime;
        }

        public double getLockMonths() {
            return lockMonths;
        }

        public void setLockMonths(double lockMonths) {
            this.lockMonths = lockMonths;
        }

        public String getSchemeCode() {
            return schemeCode;
        }

        public void setSchemeCode(String schemeCode) {
            this.schemeCode = schemeCode;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getDistributeTime() {
            return distributeTime;
        }

        public void setDistributeTime(String distributeTime) {
            this.distributeTime = distributeTime;
        }

        public List<CattleListBean> getCattleList() {
            return cattleList;
        }

        public void setCattleList(List<CattleListBean> cattleList) {
            this.cattleList = cattleList;
        }

        public  class CattleListBean {
            /**
             * cattleCode : 2222222
             * cattleImage : images/ca09570497f14175b29cc0be930be12c.png
             * cattleWeight : 100.0
             */

            private String cattleCode;
            private String cattleImage;
            private double cattleWeight;

            public String getCattleCode() {
                return cattleCode;
            }

            public void setCattleCode(String cattleCode) {
                this.cattleCode = cattleCode;
            }

            public String getCattleImage() {
                return cattleImage;
            }

            public void setCattleImage(String cattleImage) {
                this.cattleImage = cattleImage;
            }

            public double getCattleWeight() {
                return cattleWeight;
            }

            public void setCattleWeight(double cattleWeight) {
                this.cattleWeight = cattleWeight;
            }
        }
    }
}
