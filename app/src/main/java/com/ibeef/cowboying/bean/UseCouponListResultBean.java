package com.ibeef.cowboying.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/23 20:34
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class UseCouponListResultBean {

    private String code;
    private String message;
    private int pageNo;
    private int pageSize;
    private Object totalRows;
    private Object totalPages;
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

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
        this.totalPages = totalPages;
    }

    public  class BizDataBean {

        private int defautChoose;

        public int getDefautChoose() {
            return defautChoose;
        }

        public void setDefautChoose(int defautChoose) {
            this.defautChoose = defautChoose;
        }

        private String couponId;
        private String userId;
        private String code;
        private String activityId;
        private String type;
        private String useType;
        private String name;
        private String status;
        private String deleteStatus;
        private double parValue;
        private double needAmount;
        private String useStartTime;
        private String useEndTime;
        private String useSchemeType;
        private String useLockMonths;
        private String orderCode;
        private String useTime;

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
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

        public String getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(String deleteStatus) {
            this.deleteStatus = deleteStatus;
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

        public String getUseStartTime() {
            return useStartTime;
        }

        public void setUseStartTime(String useStartTime) {
            this.useStartTime = useStartTime;
        }

        public String getUseEndTime() {
            return useEndTime;
        }

        public void setUseEndTime(String useEndTime) {
            this.useEndTime = useEndTime;
        }

        public String getUseSchemeType() {
            return useSchemeType;
        }

        public void setUseSchemeType(String useSchemeType) {
            this.useSchemeType = useSchemeType;
        }

        public String getUseLockMonths() {
            return useLockMonths;
        }

        public void setUseLockMonths(String useLockMonths) {
            this.useLockMonths = useLockMonths;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }
    }
}
