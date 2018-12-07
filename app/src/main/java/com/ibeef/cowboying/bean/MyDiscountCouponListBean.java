package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/17 16:00
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyDiscountCouponListBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"couponId":107,"userId":55,"code":"SVX68VD0","activityId":33,"type":"2","useType":"0","name":"购物优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5.01,"useStartTime":1543212000000,"useEndTime":1543388400000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":108,"userId":55,"code":"YZJLUU2K","activityId":34,"type":"2","useType":"0","name":"买不了吃亏买不了上当","status":"0","deleteStatus":"0","parValue":100,"needAmount":100,"useStartTime":1543220466000,"useEndTime":1547540466000,"useSchemeType":"0","useLockMonths":0,"orderCode":null,"useTime":null},{"couponId":109,"userId":55,"code":"LUSYQ78A","activityId":37,"type":"2","useType":"0","name":"购物优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5.01,"useStartTime":1543219200000,"useEndTime":1543388400000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":110,"userId":55,"code":"WS8A9T7T","activityId":38,"type":"2","useType":"0","name":"购物优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5,"useStartTime":1543219200000,"useEndTime":1545980400000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":111,"userId":55,"code":"VXXRG4XU","activityId":41,"type":"2","useType":"0","name":"购物优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5,"useStartTime":1543219200000,"useEndTime":1545980400000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null},{"couponId":112,"userId":55,"code":"P5LBRFYZ","activityId":42,"type":"2","useType":"0","name":"购物优惠券","status":"0","deleteStatus":"0","parValue":5,"needAmount":5,"useStartTime":1543219200000,"useEndTime":1545980400000,"useSchemeType":"1","useLockMonths":null,"orderCode":null,"useTime":null}]
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

    public  class BizDataBean {
        /**
         * couponId : 107
         * userId : 55
         * code : SVX68VD0
         * activityId : 33
         * type : 2
         * useType : 0
         * name : 购物优惠券
         * status : 0
         * deleteStatus : 0
         * parValue : 5.0
         * needAmount : 5.01
         * useStartTime : 1543212000000
         * useEndTime : 1543388400000
         * useSchemeType : 1
         * useLockMonths : null
         * orderCode : null
         * useTime : null
         */

        private int couponId;
        private int userId;
        private String code;
        private int activityId;
        private Integer useProductId;
        private String type;
        private String useProductName;
        private String useType;
        private String name;
        private String status;
        private String deleteStatus;
        private double parValue;
        private double needAmount;
        private long useStartTime;
        private long useEndTime;
        private String useSchemeType;
        private Object useLockMonths;
        private Object orderCode;
        private Object useTime;

        public String getUseProductName() {
            return useProductName;
        }

        public void setUseProductName(String useProductName) {
            this.useProductName = useProductName;
        }

        public Integer getUseProductId() {
            return useProductId;
        }

        public void setUseProductId(Integer useProductId) {
            this.useProductId = useProductId;
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

        public Object getUseLockMonths() {
            return useLockMonths;
        }

        public void setUseLockMonths(Object useLockMonths) {
            this.useLockMonths = useLockMonths;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public Object getUseTime() {
            return useTime;
        }

        public void setUseTime(Object useTime) {
            this.useTime = useTime;
        }
    }
}
