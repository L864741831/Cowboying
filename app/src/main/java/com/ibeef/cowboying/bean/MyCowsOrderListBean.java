package com.ibeef.cowboying.bean;

import java.util.List;

public class MyCowsOrderListBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"orderId":176,"orderCode":"116664239775872","status":"2","cattleCount":1,"payAmount":100,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg"},{"orderId":175,"orderCode":"116663339397248","status":"2","cattleCount":1,"payAmount":100,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg"},{"orderId":173,"orderCode":"116662826946688","status":"2","cattleCount":1,"payAmount":100,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg"},{"orderId":172,"orderCode":"116662254297216","status":"2","cattleCount":1,"payAmount":100,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg"},{"orderId":171,"orderCode":"116660809916544","status":"2","cattleCount":1,"payAmount":100,"pastureName":"aaaa","pastureImage":"images/DK7tdfmFBF.jpg","cattleList":[],"schemeType":"1","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/snmACwyANx.jpg"},{"orderId":166,"orderCode":"116648627376256","status":"2","cattleCount":1,"payAmount":1,"pastureName":null,"pastureImage":null,"cattleList":[],"schemeType":"3","unlockTime":"","lockMonths":null,"orderIncome":null,"activityImageUrl":"images/fyasMdpwfs.jpg"}]
     * pageNo : 1
     * pageSize : null
     * totalRows : null
     * totalPages : null
     */

    private String code;
    private String message;
    private int pageNo;
    private int pageSize;
    private int totalRows;
    private int totalPages;
    private List<BizDataBean> bizData;

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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * orderId : 176
         * orderCode : 116664239775872
         * status : 2
         * cattleCount : 1
         * payAmount : 100.0
         * pastureName : aaaa
         * pastureImage : images/DK7tdfmFBF.jpg
         * cattleList : []
         * schemeType : 1
         * unlockTime :
         * lockMonths : null
         * orderIncome : null
         * activityImageUrl : images/snmACwyANx.jpg
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
        private List<CattleListBean> cattleList;

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

        public String getActivityImageUrl() {
            return activityImageUrl;
        }

        public void setActivityImageUrl(String activityImageUrl) {
            this.activityImageUrl = activityImageUrl;
        }

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

        public String getUnlockTime() {
            return unlockTime;
        }

        public void setUnlockTime(String unlockTime) {
            this.unlockTime = unlockTime;
        }


        public List<CattleListBean> getCattleList() {
            return cattleList;
        }

        public void setCattleList(List<CattleListBean> cattleList) {
            this.cattleList = cattleList;
        }
        public  class CattleListBean {
            /**
             * cattleCode : fdsa1174
             * cattleImage : images/6zYS7Sw5DR.jpg
             * cattleWeight : 284.6
             */

            private String cattleCode;
            private String cattleImage;
            private String cattleName;
            private double cattleWeight;
            private int defautChoose;

            public String getCattleName() {
                return cattleName;
            }

            public void setCattleName(String cattleName) {
                this.cattleName = cattleName;
            }

            public int getDefautChoose() {
                return defautChoose;
            }

            public void setDefautChoose(int defautChoose) {
                this.defautChoose = defautChoose;
            }

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
