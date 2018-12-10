package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyAfterSaleListBean {

    /**
     * code : 000000
     * message : ”“
     * bizData : [{"shopRefundOrderResVo":{"id":7,"orderId":66,"code":"120622618820736","reason":"不想要","status":"1","refundTime":54254,"amount":100,"orderStatus":"1","refuseReason":"\u201d\u201c"},"orderProductResVos":[{"productId":45,"code":"201812070004","name":"什么马","status":"0","imageUrl":"images/b6ef53ad813eb42aa1524253e98b3bbb","quantity":1,"buyPrice":1,"specification":"100g"}]},{"shopRefundOrderResVo":{"id":8,"orderId":64,"code":"120623517507712","reason":"不想要","status":"1","refundTime":null,"amount":100,"orderStatus":"1","refuseReason":null},"orderProductResVos":[{"productId":45,"code":"201812070004","name":"什么马","status":"0","imageUrl":"images/b6ef53ad813eb42aa1524253e98b3bbb","quantity":1,"buyPrice":1,"specification":"100g"}]}]
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

    public static class BizDataBean {
        /**
         * shopRefundOrderResVo : {"id":7,"orderId":66,"code":"120622618820736","reason":"不想要","status":"1","refundTime":54254,"amount":100,"orderStatus":"1","refuseReason":"\u201d\u201c"}
         * orderProductResVos : [{"productId":45,"code":"201812070004","name":"什么马","status":"0","imageUrl":"images/b6ef53ad813eb42aa1524253e98b3bbb","quantity":1,"buyPrice":1,"specification":"100g"}]
         */

        private ShopRefundOrderResVoBean shopRefundOrderResVo;
        private List<OrderProductResVosBean> orderProductResVos;

        public ShopRefundOrderResVoBean getShopRefundOrderResVo() {
            return shopRefundOrderResVo;
        }

        public void setShopRefundOrderResVo(ShopRefundOrderResVoBean shopRefundOrderResVo) {
            this.shopRefundOrderResVo = shopRefundOrderResVo;
        }

        public List<OrderProductResVosBean> getOrderProductResVos() {
            return orderProductResVos;
        }

        public void setOrderProductResVos(List<OrderProductResVosBean> orderProductResVos) {
            this.orderProductResVos = orderProductResVos;
        }

        public static class ShopRefundOrderResVoBean {
            /**
             * id : 7
             * orderId : 66
             * code : 120622618820736
             * reason : 不想要
             * status : 1
             * refundTime : 54254
             * amount : 100
             * orderStatus : 1
             * refuseReason : ”“
             */

            private int id;
            private int orderId;
            private String code;
            private String reason;
            private String status;
            private long refundTime;
            private double amount;
            private String orderStatus;
            private String refuseReason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public long getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(long refundTime) {
                this.refundTime = refundTime;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }
        }

        public static class OrderProductResVosBean {
            /**
             * productId : 45
             * code : 201812070004
             * name : 什么马
             * status : 0
             * imageUrl : images/b6ef53ad813eb42aa1524253e98b3bbb
             * quantity : 1
             * buyPrice : 1
             * specification : 100g
             */

            private int productId;
            private String code;
            private String name;
            private String status;
            private String imageUrl;
            private double quantity;
            private double buyPrice;
            private String specification;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public double getBuyPrice() {
                return buyPrice;
            }

            public void setBuyPrice(double buyPrice) {
                this.buyPrice = buyPrice;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }
        }
    }
}
