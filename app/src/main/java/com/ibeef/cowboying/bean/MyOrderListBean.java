package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyOrderListBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"latestState":null,"shopOrderResVo":{"orderId":14,"code":"120145063002240","status":"0","payStatus":"0","payAmount":5.4,"orderAmount":54,"discountAmount":0,"carrageAmount":0.3,"payTime":null,"receiveType":"1","receiveCode":"2015296363","storeId":"","deliveryTime":"","receiveTime":"","refundAmount":0,"refundTime":"\u201c\u201d","receiverName":"兔兔咯","receiverMobile":"18503876318","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"监控","cancelReason":"\u201c\u201d","createTime":"\u201c1544068291000\u201d"},"shopOrderProductResVos":[{"productId":"\u201c39\u201d","code":"201812060001","name":"牛腿肉","status":"1","imageUrl":"images/b15c113aeddbeb606d938010b88cf8e6","quantity":9,"buyPrice":6}]},{"latestState":null,"shopOrderResVo":{"orderId":15,"code":"120145927663744","status":"0","payStatus":"0","payAmount":5.33,"orderAmount":532.5,"discountAmount":0,"carrageAmount":null,"payTime":null,"receiveType":"1","receiveCode":"1501526440","storeId":null,"deliveryTime":null,"receiveTime":null,"refundAmount":null,"refundTime":null,"receiverName":"兔兔咯","receiverMobile":"18503876318","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"监控","cancelReason":null,"createTime":1544068502000},"shopOrderProductResVos":[{"productId":34,"code":"201812050017","name":"牛头","status":"1","imageUrl":"images/ca09570497f14175b29cc0be930be12c.png","quantity":5,"buyPrice":6},{"productId":35,"code":"201812050018","name":"啦啦啦啦啦啦啦啦啦啦啦","status":"1","imageUrl":"images/e71ff25f949b9d1c9d83f99e0ed99329","quantity":5,"buyPrice":0.5},{"productId":36,"code":"201812050022","name":"牛牛可以过年吗","status":"1","imageUrl":"images/7bdca3651375fbede5265a954011b8cb","quantity":5,"buyPrice":100}]},{"latestState":null,"shopOrderResVo":{"orderId":16,"code":"120146180378752","status":"0","payStatus":"0","payAmount":0.6,"orderAmount":60,"discountAmount":0,"carrageAmount":null,"payTime":null,"receiveType":"1","receiveCode":"1583757932","storeId":null,"deliveryTime":null,"receiveTime":null,"refundAmount":null,"refundTime":null,"receiverName":"兔兔咯","receiverMobile":"18503876318","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"监控","cancelReason":null,"createTime":1544068564000},"shopOrderProductResVos":[{"productId":39,"code":"201812060001","name":"牛腿肉","status":"1","imageUrl":"images/b15c113aeddbeb606d938010b88cf8e6","quantity":1,"buyPrice":60}]}]
     * pageNo : 1
     * pageSize : 10
     * totalRows : 12
     * totalPages : 12
     */

    private String code;
    private Object message;
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
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
         * latestState : null
         * shopOrderResVo : {"orderId":14,"code":"120145063002240","status":"0","payStatus":"0","payAmount":5.4,"orderAmount":54,"discountAmount":0,"carrageAmount":0.3,"payTime":null,"receiveType":"1","receiveCode":"2015296363","storeId":"","deliveryTime":"","receiveTime":"","refundAmount":0,"refundTime":"\u201c\u201d","receiverName":"兔兔咯","receiverMobile":"18503876318","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"监控","cancelReason":"\u201c\u201d","createTime":"\u201c1544068291000\u201d"}
         * shopOrderProductResVos : [{"productId":"\u201c39\u201d","code":"201812060001","name":"牛腿肉","status":"1","imageUrl":"images/b15c113aeddbeb606d938010b88cf8e6","quantity":9,"buyPrice":6}]
         */

        private Object latestState;
        private ShopOrderResVoBean shopOrderResVo;
        private List<ShopOrderProductResVosBean> shopOrderProductResVos;

        public Object getLatestState() {
            return latestState;
        }

        public void setLatestState(Object latestState) {
            this.latestState = latestState;
        }

        public ShopOrderResVoBean getShopOrderResVo() {
            return shopOrderResVo;
        }

        public void setShopOrderResVo(ShopOrderResVoBean shopOrderResVo) {
            this.shopOrderResVo = shopOrderResVo;
        }

        public List<ShopOrderProductResVosBean> getShopOrderProductResVos() {
            return shopOrderProductResVos;
        }

        public void setShopOrderProductResVos(List<ShopOrderProductResVosBean> shopOrderProductResVos) {
            this.shopOrderProductResVos = shopOrderProductResVos;
        }

        public  class ShopOrderResVoBean {
            /**
             * orderId : 14
             * code : 120145063002240
             * status : 0
             * payStatus : 0
             * payAmount : 5.4
             * orderAmount : 54.0
             * discountAmount : 0.0
             * carrageAmount : 0.3
             * payTime : null
             * receiveType : 1
             * receiveCode : 2015296363
             * storeId :
             * deliveryTime :
             * receiveTime :
             * refundAmount : 0.0
             * refundTime : “”
             * receiverName : 兔兔咯
             * receiverMobile : 18503876318
             * receiverProvince : 330000
             * receiverCity : 330100
             * receiverRegion : 330108
             * receiverAddress : 监控
             * cancelReason : “”
             * createTime : “1544068291000”
             */

            private int orderId;
            private String code;
            private String status;
            private String payStatus;
            private double payAmount;
            private double orderAmount;
            private double discountAmount;
            private double carrageAmount;
            private Object payTime;
            private String receiveType;
            private String receiveCode;
            private String storeId;
            private String deliveryTime;
            private String receiveTime;
            private double refundAmount;
            private String refundTime;
            private String receiverName;
            private String receiverMobile;
            private int receiverProvince;
            private int receiverCity;
            private int receiverRegion;
            private String receiverAddress;
            private String cancelReason;
            private String createTime;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public double getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(double payAmount) {
                this.payAmount = payAmount;
            }

            public double getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(double orderAmount) {
                this.orderAmount = orderAmount;
            }

            public double getDiscountAmount() {
                return discountAmount;
            }

            public void setDiscountAmount(double discountAmount) {
                this.discountAmount = discountAmount;
            }

            public double getCarrageAmount() {
                return carrageAmount;
            }

            public void setCarrageAmount(double carrageAmount) {
                this.carrageAmount = carrageAmount;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public String getReceiveType() {
                return receiveType;
            }

            public void setReceiveType(String receiveType) {
                this.receiveType = receiveType;
            }

            public String getReceiveCode() {
                return receiveCode;
            }

            public void setReceiveCode(String receiveCode) {
                this.receiveCode = receiveCode;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(String deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public String getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(String receiveTime) {
                this.receiveTime = receiveTime;
            }

            public double getRefundAmount() {
                return refundAmount;
            }

            public void setRefundAmount(double refundAmount) {
                this.refundAmount = refundAmount;
            }

            public String getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(String refundTime) {
                this.refundTime = refundTime;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverMobile() {
                return receiverMobile;
            }

            public void setReceiverMobile(String receiverMobile) {
                this.receiverMobile = receiverMobile;
            }

            public int getReceiverProvince() {
                return receiverProvince;
            }

            public void setReceiverProvince(int receiverProvince) {
                this.receiverProvince = receiverProvince;
            }

            public int getReceiverCity() {
                return receiverCity;
            }

            public void setReceiverCity(int receiverCity) {
                this.receiverCity = receiverCity;
            }

            public int getReceiverRegion() {
                return receiverRegion;
            }

            public void setReceiverRegion(int receiverRegion) {
                this.receiverRegion = receiverRegion;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getCancelReason() {
                return cancelReason;
            }

            public void setCancelReason(String cancelReason) {
                this.cancelReason = cancelReason;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }

        public  class ShopOrderProductResVosBean {
            /**
             * productId : “39”
             * code : 201812060001
             * name : 牛腿肉
             * status : 1
             * imageUrl : images/b15c113aeddbeb606d938010b88cf8e6
             * quantity : 9
             * buyPrice : 6.0
             */

            private String productId;
            private String code;
            private String name;
            private String status;
            private String imageUrl;
            private int quantity;
            private double buyPrice;
            private String specification;

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }


            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
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

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getBuyPrice() {
                return buyPrice;
            }

            public void setBuyPrice(double buyPrice) {
                this.buyPrice = buyPrice;
            }
        }
    }
}
