package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/13 17:46
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ActiveSchemeResultBean implements Serializable {

    /**
     * code : 000000
     * message :
     * bizData : [{"schemeId":14,"pastureId":52,"pastureName":"答复","type":"1","expectRate":"12%-78%","realRate":45,"price":500000,"vipLevel":"4","totalStock":10,"stock":10,"startTime":1542211200000,"endTime":1543334400000,"status":"1","code":"20181112005","imageUrl":"images/933a2e9163014d808adb29a74b0091c2.jpg","createTime":1542019005000,"updateTime":1542100141000,"lockMonths":null,"projectDescribe":"\u201c\u201d","curStatus":"2"},{"schemeId":1,"pastureId":35,"pastureName":"撒","type":"1","expectRate":"8%-9%","realRate":9,"price":500000,"vipLevel":"1","totalStock":1000,"stock":0,"startTime":1541918515000,"endTime":1554100918000,"status":"1","code":"11111111","imageUrl":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","createTime":1541572953000,"updateTime":1542160815000,"lockMonths":2,"projectDescribe":"","curStatus":"3"}]
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
         * schemeId : 14
         * pastureId : 52
         * pastureName : 答复
         * type : 1
         * expectRate : 12%-78%
         * realRate : 45.0
         * price : 500000.0
         * vipLevel : 4
         * totalStock : 10
         * stock : 10
         * startTime : 1542211200000
         * endTime : 1543334400000
         * status : 1
         * code : 20181112005
         * imageUrl : images/933a2e9163014d808adb29a74b0091c2.jpg
         * createTime : 1542019005000
         * updateTime : 1542100141000
         * lockMonths : null
         * projectDescribe : “”
         * curStatus : 2
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
        private String lockMonths;
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

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getCreateTime() {
            return createTime;
        }

        public String getUpdateTime() {
            return updateTime;
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

        public String getCurStatus() {
            return curStatus;
        }

        public void setCurStatus(String curStatus) {
            this.curStatus = curStatus;
        }
    }
}
