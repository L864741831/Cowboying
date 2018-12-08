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
     * message :
     * bizData : [{"couponId":207,"useType":"0","parValue":123,"needAmount":123,"useEndTime":1545148799000,"useProductId":null},{"couponId":312,"useType":"3","parValue":105,"needAmount":106,"useEndTime":1544630399000,"useProductId":null},{"couponId":313,"useType":"3","parValue":105,"needAmount":106,"useEndTime":1544630399000,"useProductId":null}]
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

    public  class BizDataBean implements Serializable{
        /**
         * couponId : 207
         * useType : 0
         * parValue : 123.0
         * needAmount : 123.0
         * useEndTime : 1545148799000
         * useProductId : null
         */

        private int couponId;
        private String status;
        private int defautChoose;
        private String useType;
        private String name;
        private double parValue;
        private double needAmount;
        private long useEndTime;
        private Object useProductId;

        public int getDefautChoose() {
            return defautChoose;
        }

        public void setDefautChoose(int defautChoose) {
            this.defautChoose = defautChoose;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
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

        public long getUseEndTime() {
            return useEndTime;
        }

        public void setUseEndTime(long useEndTime) {
            this.useEndTime = useEndTime;
        }

        public Object getUseProductId() {
            return useProductId;
        }

        public void setUseProductId(Object useProductId) {
            this.useProductId = useProductId;
        }
    }
}
