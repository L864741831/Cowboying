package com.ibeef.cowboying.bean;

import java.util.List;

public class MyCowsOrderListDetailBean {


    /**
     * code : 000000
     * message :
     * bizData : {"orderId":158,"orderCode":"116639270654080","status":"2","cattleCount":1,"payAmount":null,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":null,"lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg","schemeCode":"20181123001","price":100,"discountAmount":null,"payType":"3","createTime":"2018-11-26 14:06:24","payTime":"2018-11-26 14:14:56","distributeTime":null,"sellTime":null,"sellSuccessTime":null,"closeTime":"2018-11-26 14:14:56","experienceDays":null}
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
         * orderId : 158
         * orderCode : 116639270654080
         * status : 2
         * cattleCount : 1
         * payAmount : null
         * pastureName : aaaa
         * pastureImage : images/DK7tdfmFBF.jpg
         * cattleList : []
         * schemeType : 1
         * unlockTime : null
         * lockMonths : null
         * orderIncome : null
         * activityImageUrl : images/snmACwyANx.jpg
         * schemeCode : 20181123001
         * price : 100.0
         * discountAmount : null
         * payType : 3
         * createTime : 2018-11-26 14:06:24
         * payTime : 2018-11-26 14:14:56
         * distributeTime : null
         * sellTime : null
         * sellSuccessTime : null
         * closeTime : 2018-11-26 14:14:56
         * experienceDays : null
         */

        private int orderId;
        private String orderCode;
        private String status;
        private int cattleCount;
        private double payAmount;
        private String pastureName;
        private String pastureImage;
        private String schemeType;
        private String unlockTime;
        private int lockMonths;
        private double orderIncome;
        private String activityImageUrl;
        private String schemeCode;
        private double price;
        private double discountAmount;
        private String payType;
        private String createTime;
        private String payTime;
        private String distributeTime;
        private String sellTime;
        private String sellSuccessTime;
        private String closeTime;
        private String experienceDays;
        private List<CattleListBean > cattleList;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
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

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public String getUnlockTime() {
            return unlockTime;
        }

        public void setUnlockTime(String unlockTime) {
            this.unlockTime = unlockTime;
        }

        public int getLockMonths() {
            return lockMonths;
        }

        public void setLockMonths(int lockMonths) {
            this.lockMonths = lockMonths;
        }

        public double getOrderIncome() {
            return orderIncome;
        }

        public void setOrderIncome(double orderIncome) {
            this.orderIncome = orderIncome;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getDistributeTime() {
            return distributeTime;
        }

        public void setDistributeTime(String distributeTime) {
            this.distributeTime = distributeTime;
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

        public String getExperienceDays() {
            return experienceDays;
        }

        public void setExperienceDays(String experienceDays) {
            this.experienceDays = experienceDays;
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

        public String getSchemeType() {
            return schemeType;
        }

        public void setSchemeType(String schemeType) {
            this.schemeType = schemeType;
        }


        public String getActivityImageUrl() {
            return activityImageUrl;
        }

        public void setActivityImageUrl(String activityImageUrl) {
            this.activityImageUrl = activityImageUrl;
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

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
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
