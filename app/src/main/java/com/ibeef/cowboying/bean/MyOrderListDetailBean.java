package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyOrderListDetailBean {


    /**
     * code : 000000
     * message :
     * bizData : {"latestState":null,"shopOrderResVo":{"orderId":25,"code":"120198223028352","status":"0","payStatus":"1","payAmount":0.01,"orderAmount":1,"discountAmount":0,"carrageAmount":null,"payTime":1544081278000,"receiveType":"1","receiveCode":"1229289425","storeId":null,"deliveryTime":null,"receiveTime":null,"refundAmount":null,"refundTime":null,"receiverName":"屠龙记","receiverMobile":"18703643373","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"下午","cancelReason":null,"createTime":1544081269000},"shopOrderProductResVos":[{"productId":19,"code":"201812040013","name":"来两块的吗","status":"1","imageUrl":"images/1cf94a995c9d32b694afb7b245e2f997","quantity":1,"buyPrice":0.01}]}
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
         * latestState :{\"context\":\"[上海市]快件已发车\",\"status\":\"在途\",\"time\":\"2018-12-03 15:59:41\"}
         * shopOrderResVo : {"orderId":25,"code":"120198223028352","status":"0","payStatus":"1","payAmount":0.01,"orderAmount":1,"discountAmount":0,"carrageAmount":null,"payTime":1544081278000,"receiveType":"1","receiveCode":"1229289425","storeId":null,"deliveryTime":null,"receiveTime":null,"refundAmount":null,"refundTime":null,"receiverName":"屠龙记","receiverMobile":"18703643373","receiverProvince":330000,"receiverCity":330100,"receiverRegion":330108,"receiverAddress":"下午","cancelReason":null,"createTime":1544081269000}
         * shopOrderProductResVos : [{"productId":19,"code":"201812040013","name":"来两块的吗","status":"1","imageUrl":"images/1cf94a995c9d32b694afb7b245e2f997","quantity":1,"buyPrice":0.01}]
         */
//        "storeInfoResVo":{"storeId":1,"name":"啊啊啊","address":"嘻嘻嘻","status":"1","imageUrl":"images/ca09570497f14175b29cc0be930be12c.png"}

        private ExpressResvo expressResvo;
        private ShopOrderResVoBean shopOrderResVo;
        private StoreInfoResVo storeInfoResVo;
        private List<ShopOrderProductResVosBean> shopOrderProductResVos;
        private String refundId;

        public String getRefundId() {
            return refundId;
        }

        public void setRefundId(String refundId) {
            this.refundId = refundId;
        }

        public ExpressResvo getLatestState() {
            return expressResvo;
        }

        public void setExpressResvo(ExpressResvo expressResvo) {
            this.expressResvo = expressResvo;
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

        public StoreInfoResVo getStoreInfoResVo() {
            return storeInfoResVo;
        }

        public void setStoreInfoResVo(StoreInfoResVo storeInfoResVo) {
            this.storeInfoResVo = storeInfoResVo;
        }

        public  class ShopOrderResVoBean {
            /**
             * orderId : 25
             * code : 120198223028352
             * status : 0
             * payStatus : 1
             * payAmount : 0.01
             * orderAmount : 1.0
             * discountAmount : 0.0
             * carrageAmount : null
             * payTime : 1544081278000
             * receiveType : 1
             * receiveCode : 1229289425
             * storeId : null
             * deliveryTime : null
             * receiveTime : null
             * refundAmount : null
             * refundTime : null
             * receiverName : 屠龙记
             * receiverMobile : 18703643373
             * receiverProvince : 330000
             * receiverCity : 330100
             * receiverRegion : 330108
             * receiverAddress : 下午
             * cancelReason : null
             * createTime : 1544081269000
             */

            private int orderId;
            private String code;
            private String status;
            private String payStatus;
            private double payAmount;
            private double orderAmount;
            private double discountAmount;
            private double carrageAmount;
            private long payTime;
            private String receiveType;
            private String receiveCode;
            private Object storeId;
            private long deliveryTime;
            private long receiveTime;
            private Object refundAmount;
            private long refundTime;
            private String receiverName;
            private String receiverMobile;
            private int receiverProvince;
            private int receiverCity;
            private int receiverRegion;
            private String receiverAddress;
            private Object cancelReason;
            private long createTime;

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            private String updateTime;

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

            public long getPayTime() {
                return payTime;
            }

            public void setPayTime(long payTime) {
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

            public Object getStoreId() {
                return storeId;
            }

            public void setStoreId(Object storeId) {
                this.storeId = storeId;
            }

            public long getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(long deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public long getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(long receiveTime) {
                this.receiveTime = receiveTime;
            }

            public Object getRefundAmount() {
                return refundAmount;
            }

            public void setRefundAmount(Object refundAmount) {
                this.refundAmount = refundAmount;
            }

            public long getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(long refundTime) {
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

            public Object getCancelReason() {
                return cancelReason;
            }

            public void setCancelReason(Object cancelReason) {
                this.cancelReason = cancelReason;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public  class ExpressResvo {

//          {\"context\":\"[上海市]快件已发车\",\"status\":\"在途\",\"time\":\"2018-12-03 15:59:41\"}

            private String context;
            private String status;
            private String time;

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }



        }

        public  class StoreInfoResVo {

//         "storeInfoResVo":{"storeId":1,"name":"啊啊啊","address":"嘻嘻嘻","status":"1","imageUrl":"images/ca09570497f14175b29cc0be930be12c.png"}}

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            private String storeId;
            private String name;
            private String address;
            private String status;
            private String imageUrl;



        }

        public  class ShopOrderProductResVosBean {
            /**
             * productId : 19
             * code : 201812040013
             * name : 来两块的吗
             * status : 1
             * imageUrl : images/1cf94a995c9d32b694afb7b245e2f997
             * quantity : 1
             * buyPrice : 0.01
             */

            private int productId;
            private String code;
            private String name;
            private String status;
            private String imageUrl;
            private String specification;
            private int quantity;
            private double buyPrice;

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

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
