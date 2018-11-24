package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/13 17:47
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HistorySchemeResultBean implements Serializable {

    /**
     * code : 000000
     * message :
     * bizData : [{"schemeId":8,"pastureId":35,"pastureName":"撒","type":"2","expectRate":"8%-9%","realRate":8,"price":500000,"vipLevel":"1","totalStock":1000,"stock":0,"startTime":1541572915000,"endTime":1541572915000,"status":"9","code":"20181109004","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541749923000,"updateTime":null,"lockMonths":6,"projectDescribe":"","curStatus":""},{"schemeId":7,"pastureId":35,"pastureName":"撒","type":"2","expectRate":"8%-9%","realRate":8,"price":500000,"vipLevel":"1","totalStock":1000,"stock":50,"startTime":1541572915000,"endTime":1541572915000,"status":"2","code":"20181109003","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541747027000,"updateTime":null,"lockMonths":6,"projectDescribe":"","curStatus":""},{"schemeId":4,"pastureId":35,"pastureName":"撒","type":"2","expectRate":"8%-9%","realRate":8,"price":500000,"vipLevel":"1","totalStock":1000,"stock":50,"startTime":1541572915000,"endTime":1541659315000,"status":"1","code":"20181108007","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541673677000,"updateTime":null,"lockMonths":6,"projectDescribe":"","curStatus":""},{"schemeId":3,"pastureId":35,"pastureName":"撒","type":"1","expectRate":"8%-9%","realRate":8,"price":500000,"vipLevel":"1","totalStock":1000,"stock":50,"startTime":1541572915000,"endTime":1541918515000,"status":"1","code":"20181108006","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541673413000,"updateTime":null,"lockMonths":null,"projectDescribe":null,"curStatus":null},{"schemeId":2,"pastureId":35,"pastureName":"撒","type":"2","expectRate":"8%-9%","realRate":8,"price":500000,"vipLevel":"1","totalStock":1000,"stock":50,"startTime":1541572915000,"endTime":1541745715000,"status":"1","code":"11","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541673088000,"updateTime":1542001833000,"lockMonths":3,"projectDescribe":"","curStatus":""}]
     * pageNo : 1
     * pageSize : 10
     * totalRows : 1
     * totalPages : 1
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

    public  class BizDataBean implements Serializable{
        /**
         * schemeId : 8
         * pastureId : 35
         * pastureName : 撒
         * type : 2
         * expectRate : 8%-9%
         * realRate : 8.0
         * price : 500000.0
         * vipLevel : 1
         * totalStock : 1000
         * stock : 0
         * startTime : 1541572915000
         * endTime : 1541572915000
         * status : 9
         * code : 20181109004
         * imageUrl : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * createTime : 1541749923000
         * updateTime : null
         * lockMonths : 6
         * projectDescribe :
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

        private int participatorNum;

        public int getParticipatorNum() {
            return participatorNum;
        }

        public void setParticipatorNum(int participatorNum) {
            this.participatorNum = participatorNum;
        }

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
