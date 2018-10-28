package com.ibeef.cowboying.bean;

import java.util.List;

public class RanchDynamicListBean {

    private String status;
    private String message;
    private int currentPage;
    private int pageSize;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private double parValue;
        private double needAmount;
        private Object useStartTime;
        private Object useEndTime;
        private String couponType;
        private int couponId;
        private int shopId;
        private String shopName;
        private Object productId;
        private String status;



        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getParValue() {
            return parValue;
        }

        public void setParValue(double parValue) {
            this.parValue = parValue;
        }

        public double getNeedAmount() {
            return needAmount;
        }

        public void setNeedAmount(double needAmount) {
            this.needAmount = needAmount;
        }

        public Object getUseStartTime() {
            return useStartTime;
        }

        public void setUseStartTime(Object useStartTime) {
            this.useStartTime = useStartTime;
        }

        public Object getUseEndTime() {
            return useEndTime;
        }

        public void setUseEndTime(Object useEndTime) {
            this.useEndTime = useEndTime;
        }

        public String getCouponType() {
            return couponType;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }
    }
}
