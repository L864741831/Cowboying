package com.ibeef.cowboying.bean;

import java.util.List;

public class MyCowsOrderListDetailBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"orderCode":"123457","status":"3","cattleCount":2,"orderAmount":5000,"pastureName":"撒","pastureImage":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","cattleList":[{"cattleCode":"zjll653.9439865162196","cattleImage":"images/31fa1235cce759035cf3ca3996a33b55.png","cattleWeight":244.3},{"cattleCode":"zjll338.83437160107354","cattleImage":"images/31fa1235cce759035cf3ca3996a33b55.png","cattleWeight":344.4}],"adoptType":"2","unlockTime":"2019-01-30","lockMonths":3}]
     */

    private String code;
    private Object message;
    private List<BizDataBean> bizData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public static class BizDataBean {
        /**
         * orderCode : 123457
         * status : 3
         * cattleCount : 2
         * orderAmount : 5000
         * pastureName : 撒
         * pastureImage : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * cattleList : [{"cattleCode":"zjll653.9439865162196","cattleImage":"images/31fa1235cce759035cf3ca3996a33b55.png","cattleWeight":244.3},{"cattleCode":"zjll338.83437160107354","cattleImage":"images/31fa1235cce759035cf3ca3996a33b55.png","cattleWeight":344.4}]
         * adoptType : 2
         * unlockTime : 2019-01-30
         * lockMonths : 3
         */

        private String orderCode;
        private String status;
        private int cattleCount;
        private double orderAmount;
        private String pastureName;
        private String pastureImage;
        private String adoptType;
        private String unlockTime;
        private int lockMonths;
        private List<CattleListBean> cattleList;

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

        public int getLockMonths() {
            return lockMonths;
        }

        public void setLockMonths(int lockMonths) {
            this.lockMonths = lockMonths;
        }

        public List<CattleListBean> getCattleList() {
            return cattleList;
        }

        public void setCattleList(List<CattleListBean> cattleList) {
            this.cattleList = cattleList;
        }

        public static class CattleListBean {
            /**
             * cattleCode : zjll653.9439865162196
             * cattleImage : images/31fa1235cce759035cf3ca3996a33b55.png
             * cattleWeight : 244.3
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
