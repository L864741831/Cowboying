package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ls
 * @date on 2018/11/14 16:58
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CreatOderResultBean implements Serializable {

    /**
     * code : 000000
     * message : “”
     * bizData : {"orderId":21,"code":"112445580996736","schemeId":29,"userId":45,"payType":"\u201c\u201d","payAmount":500000,"payTime":"\u201c\u201d","payStatus":"0","orderAmount":500000,"discountAmount":0,"orderStatus":"1","quantity":1,"realName":"ggg","cardNo":"15558999","mobile":"18703643373","distributeTime":null,"unlockDate":"\u201c\u201d","deleteStatus":"0","updateTime":"\u201c\u201d","createTime":1542188534425,"tradeno":"\u201c\u201d"}
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

    public  class BizDataBean implements Serializable{
        /**
         * orderId : 21
         * code : 112445580996736
         * schemeId : 29
         * userId : 45
         * payType : “”
         * payAmount : 500000.0
         * payTime : “”
         * payStatus : 0
         * orderAmount : 500000.0
         * discountAmount : 0
         * orderStatus : 1
         * quantity : 1
         * realName : ggg
         * cardNo : 15558999
         * mobile : 18703643373
         * distributeTime : null
         * unlockDate : “”
         * deleteStatus : 0
         * updateTime : “”
         * createTime : 1542188534425
         * tradeno : “”
         */

        private int orderId;
        private String code;
        private int schemeId;
        private int userId;
        private String payType;
        private BigDecimal payAmount;
        private String payTime;
        private String payStatus;
        private BigDecimal orderAmount;
        private BigDecimal discountAmount;
        private String orderStatus;
        private int quantity;
        private String realName;
        private String cardNo;
        private String mobile;
        private String distributeTime;
        private String unlockDate;
        private String deleteStatus;
        private String updateTime;
        private long createTime;
        private String tradeno;

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

        public int getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(int schemeId) {
            this.schemeId = schemeId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }



        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }

        public BigDecimal getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(BigDecimal orderAmount) {
            this.orderAmount = orderAmount;
        }

        public BigDecimal getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getDistributeTime() {
            return distributeTime;
        }

        public void setDistributeTime(String distributeTime) {
            this.distributeTime = distributeTime;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }


        public String getUnlockDate() {
            return unlockDate;
        }

        public void setUnlockDate(String unlockDate) {
            this.unlockDate = unlockDate;
        }

        public String getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(String deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }
    }
}
