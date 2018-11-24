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
     * message : null
     * bizData : {"orderId":39,"code":"112685899219072","schemeId":63,"userId":45,"payType":null,"payAmount":5000,"payTime":null,"payStatus":"0","orderAmount":500000,"discountAmount":0,"orderStatus":"1","quantity":1,"realName":"李闪闪","cardNo":"410223199005143549","mobile":"18703643373","distributeTime":null,"unlockDate":null,"deleteStatus":"0","updateTime":null,"createTime":1542247205864,"tradeno":null,"nickName":"PP70PDHRWO","headImage":"images/4be2caa5391dfff1f1c0e206a84e285e.jpg","schemeCode":"20181114048","pastureName":"答复","price":5000}
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
         * orderId : 39
         * code : 112685899219072
         * schemeId : 63
         * userId : 45
         * payType : null
         * payAmount : 5000.0
         * payTime : null
         * payStatus : 0
         * orderAmount : 500000.0
         * discountAmount : 0
         * orderStatus : 1
         * quantity : 1
         * realName :
         * cardNo :
         * mobile : 18703643373
         * distributeTime : null
         * unlockDate : null
         * deleteStatus : 0
         * updateTime : null
         * createTime : 1542247205864
         * tradeno : null
         * nickName : PP70PDHRWO
         * headImage : images/4be2caa5391dfff1f1c0e206a84e285e.jpg
         * schemeCode : 20181114048
         * pastureName : 答复
         * price : 5000.0
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
        private String createTime;
        private String tradeno;
        private String nickName;
        private String headImage;
        private String schemeCode;
        private String pastureName;
        private BigDecimal price;

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


        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
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


        public String getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(String deleteStatus) {
            this.deleteStatus = deleteStatus;
        }


        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getSchemeCode() {
            return schemeCode;
        }

        public void setSchemeCode(String schemeCode) {
            this.schemeCode = schemeCode;
        }

        public String getPastureName() {
            return pastureName;
        }

        public void setPastureName(String pastureName) {
            this.pastureName = pastureName;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
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

        public String getUnlockDate() {
            return unlockDate;
        }

        public void setUnlockDate(String unlockDate) {
            this.unlockDate = unlockDate;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
