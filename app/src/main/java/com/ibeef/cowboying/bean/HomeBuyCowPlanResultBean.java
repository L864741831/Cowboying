package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2019/1/10 19:37
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeBuyCowPlanResultBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"schemeId":121,"pastureId":68,"pastureName":null,"type":"3","expectRate":"45.0%","realRate":38,"price":500000,"vipLevel":null,"totalStock":100,"stock":100,"startTime":null,"endTime":null,"status":"1","code":"20181124010","imageUrl":"sdasds","createTime":1543039685000,"updateTime":1547095264000,"lockMonths":null,"projectDescribe":"新人方案","curStatus":null,"participatorNum":null},{"schemeId":167,"pastureId":35,"pastureName":null,"type":"1","expectRate":"12.0%-78.0%","realRate":45,"price":1,"vipLevel":"1","totalStock":100,"stock":97,"startTime":"2019-01-07 00:00:00","endTime":"2019-01-12 00:00:00","status":"1","code":"20190107001","imageUrl":"images/d7848288f18eec6082b175c3a1706157.jpg","createTime":1546855714000,"updateTime":1546856784000,"lockMonths":null,"projectDescribe":null,"curStatus":null,"participatorNum":null},{"schemeId":76,"pastureId":35,"pastureName":null,"type":"1","expectRate":"8.0%-9.0%","realRate":9,"price":1,"vipLevel":"1","totalStock":100,"stock":95,"startTime":"2018-12-06 14:41:55","endTime":"2019-02-28 14:41:55","status":"1","code":"20181116004","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1542337736000,"updateTime":1545718596000,"lockMonths":null,"projectDescribe":"","curStatus":null,"participatorNum":null},{"schemeId":1,"pastureId":35,"pastureName":"","type":"1","expectRate":"8%-9%","realRate":9,"price":500000,"vipLevel":"1","totalStock":1000,"stock":1,"startTime":"2018-11-11 14:41:55","endTime":"2019-04-01 14:41:58","status":"0","code":"11111111","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541572953000,"updateTime":1545710456000,"lockMonths":2,"projectDescribe":null,"curStatus":null,"participatorNum":null}]
     */

    private String code;
    private String message;
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

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * schemeId : 121
         * pastureId : 68
         * pastureName : null
         * type : 3
         * expectRate : 45.0%
         * realRate : 38.0
         * price : 500000.0
         * vipLevel : null
         * totalStock : 100
         * stock : 100
         * startTime : null
         * endTime : null
         * status : 1
         * code : 20181124010
         * imageUrl : sdasds
         * createTime : 1543039685000
         * updateTime : 1547095264000
         * lockMonths : null
         * projectDescribe : 新人方案
         * curStatus : null
         * participatorNum : null
         */

        private int schemeId;
        private int pastureId;
        private String pastureName;
        private String type;
        private String expectRate;
        private double realRate;
        private double price;
        private Object vipLevel;
        private int totalStock;
        private int stock;
        private Object startTime;
        private Object endTime;
        private String status;
        private String code;
        private String imageUrl;
        private long createTime;
        private long updateTime;
        private String lockMonths;
        private String projectDescribe;
        private Object curStatus;
        private Object participatorNum;

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

        public double getRealRate() {
            return realRate;
        }

        public void setRealRate(double realRate) {
            this.realRate = realRate;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(Object vipLevel) {
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

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getLockMonths() {
            return lockMonths;
        }

        public void setLockMonths(String lockMonths) {
            this.lockMonths = lockMonths;
        }

        public String getProjectDescribe() {
            return projectDescribe;
        }

        public void setProjectDescribe(String projectDescribe) {
            this.projectDescribe = projectDescribe;
        }

        public Object getCurStatus() {
            return curStatus;
        }

        public void setCurStatus(Object curStatus) {
            this.curStatus = curStatus;
        }

        public Object getParticipatorNum() {
            return participatorNum;
        }

        public void setParticipatorNum(Object participatorNum) {
            this.participatorNum = participatorNum;
        }
    }
}
