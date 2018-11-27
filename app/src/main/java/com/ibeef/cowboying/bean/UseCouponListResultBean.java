package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/23 20:34
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class UseCouponListResultBean implements Serializable {


    /**
     * code : 000000
     * message : null
     * bizData : [{"couponId":103,"userId":82,"code":"DHVYJBKJ","activityId":1,"type":"1","useType":"1","name":"注册优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5.01,"useStartTime":1543201200000,"useEndTime":1543302000000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":104,"userId":82,"code":"NEFPAJWG","activityId":5,"type":"1","useType":"1","name":"注册优惠券","status":"0","deleteStatus":"0","parValue":20,"needAmount":20,"useStartTime":1543035600000,"useEndTime":1543302000000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":105,"userId":82,"code":"ZFAV3VSN","activityId":7,"type":"1","useType":"0","name":"啦啦啦","status":"0","deleteStatus":"0","parValue":100,"needAmount":100,"useStartTime":1543075200000,"useEndTime":1543593599000,"useSchemeType":"0","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":106,"userId":82,"code":"3ZX4ZXJ7","activityId":10,"type":"1","useType":"0","name":"注册优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5.01,"useStartTime":1543201200000,"useEndTime":1543302000000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null}]
     * pageNo : 10
     * pageSize : 10
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

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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


    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean implements Serializable {
        /**
         * couponId : 103
         * userId : 82
         * code : DHVYJBKJ
         * activityId : 1
         * type : 1
         * useType : 1
         * name : 注册优惠券
         * status : 0
         * deleteStatus : 0
         * parValue : 5.0
         * needAmount : 5.01
         * useStartTime : 1543201200000
         * useEndTime : 1543302000000
         * useSchemeType : 1
         * useLockMonths : null
         * orderCode : null
         * useTime : null
         */

        private int couponId;
        private int userId;
        private String code;
        private int activityId;
        private String type;
        private String useType;
        private String name;
        private String status;
        private String deleteStatus;
        private double parValue;
        private double needAmount;
        private long useStartTime;
        private long useEndTime;
        private String useSchemeType;
        private String useLockMonths;
        private String orderCode;
        private String useTime;

        private int defautChoose;

        public int getDefautChoose() {
            return defautChoose;
        }

        public void setDefautChoose(int defautChoose) {
            this.defautChoose = defautChoose;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
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

        public long getUseStartTime() {
            return useStartTime;
        }

        public void setUseStartTime(long useStartTime) {
            this.useStartTime = useStartTime;
        }

        public long getUseEndTime() {
            return useEndTime;
        }

        public void setUseEndTime(long useEndTime) {
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
