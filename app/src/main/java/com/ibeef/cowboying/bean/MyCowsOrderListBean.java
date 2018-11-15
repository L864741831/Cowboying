package com.ibeef.cowboying.bean;

import java.math.BigDecimal;
import java.util.List;

public class MyCowsOrderListBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"orderCode":"4165161644893","status":"6","cattleCount":1,"orderAmount":15000,"pastureName":"撒","pastureImage":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","cattleList":[{"cattleCode":"fdsa1174","cattleImage":"images/6zYS7Sw5DR.jpg","cattleWeight":284.6}],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112445580996736","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"啦啦啦","pastureImage":"images/F6aYnxzW3M.jpg","cattleList":[],"adoptType":"2","unlockTime":"","lockMonths":6},{"orderCode":"112456184430720","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112458319741056","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112458743509120","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112458874536064","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112458945257600","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112459028017280","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112459350483072","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null},{"orderCode":"112461220872320","status":"1","cattleCount":1,"orderAmount":5000,"pastureName":"答复","pastureImage":"images/et6DxTSP88.jpg","cattleList":[],"adoptType":"1","unlockTime":"","lockMonths":null}]
     * pageNo : 1
     * pageSize : null
     * totalRows : null
     * totalPages : null
     */

    private String code;
    private String message;
    private int pageNo;
    private Object pageSize;
    private Object totalRows;
    private Object totalPages;
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

    public Object getPageSize() {
        return pageSize;
    }

    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
    }

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
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
         * orderCode : 4165161644893
         * status : 6
         * cattleCount : 1
         * orderAmount : 15000.0
         * pastureName : 撒
         * pastureImage : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * cattleList : [{"cattleCode":"fdsa1174","cattleImage":"images/6zYS7Sw5DR.jpg","cattleWeight":284.6}]
         * adoptType : 1
         * unlockTime :
         * lockMonths : null
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

        public void setLockMonths(int lockMonths) {
            this.lockMonths = lockMonths;
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
