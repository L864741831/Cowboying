package com.ibeef.cowboying.bean;

import java.math.BigDecimal;

/**
 * @author ls
 * @date on 2018/11/14 10:56
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SchemeDetailReultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"schemeId":28,"pastureId":60,"pastureName":"","type":"2","expectRate":"8%","realRate":8,"price":5000,"vipLevel":"1","totalStock":1,"stock":1,"startTime":"2018-11-11","endTime":"2018-11-21","status":"1","code":"20181114013","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":"1542168083000","updateTime":"","lockMonths":6,"projectDescribe":"项目介绍","curStatus":""}
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
         * schemeId : 28
         * pastureId : 60
         * pastureName :
         * type : 2
         * expectRate : 8%
         * realRate : 8.0
         * price : 5000.0
         * vipLevel : 1
         * totalStock : 1
         * stock : 1
         * startTime : 2018-11-11
         * endTime : 2018-11-21
         * status : 1
         * code : 20181114013
         * imageUrl : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * createTime : 1542168083000
         * updateTime :
         * lockMonths : 6
         * projectDescribe : 项目介绍
         * curStatus :
         */

        private int schemeId;
        private int pastureId;
        private String pastureName;
        private String type;
        private String expectRate;
        private BigDecimal realRate;
        private BigDecimal price;
        private String vipLevel;
        private int totalStock;
        private int stock;
        private String startTime;
        private String endTime;
        private String status;
        private String code;
        private String imageUrl;
        private String createTime;
        private String updateTime;
        private int lockMonths;
        private String projectDescribe;
        private String curStatus;

        public int getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(int schemeId) {
            this.schemeId = schemeId;
        }

        public int getPastureId() {
            return pastureId;
        }

        public void setPastureId(int pastureId) {
            this.pastureId = pastureId;
        }

        public String getPastureName() {
            return pastureName;
        }

        public void setPastureName(String pastureName) {
            this.pastureName = pastureName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExpectRate() {
            return expectRate;
        }

        public void setExpectRate(String expectRate) {
            this.expectRate = expectRate;
        }

        public BigDecimal getRealRate() {
            return realRate;
        }

        public void setRealRate(BigDecimal realRate) {
            this.realRate = realRate;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public int getTotalStock() {
            return totalStock;
        }

        public void setTotalStock(int totalStock) {
            this.totalStock = totalStock;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getLockMonths() {
            return lockMonths;
        }

        public void setLockMonths(int lockMonths) {
            this.lockMonths = lockMonths;
        }

        public String getProjectDescribe() {
            return projectDescribe;
        }

        public void setProjectDescribe(String projectDescribe) {
            this.projectDescribe = projectDescribe;
        }

        public String getCurStatus() {
            return curStatus;
        }

        public void setCurStatus(String curStatus) {
            this.curStatus = curStatus;
        }
    }
}
