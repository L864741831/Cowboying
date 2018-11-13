package com.ibeef.cowboying.bean;

import java.math.BigDecimal;

/**
 * @author ls
 * @date on 2018/11/12 14:05
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CowManInfosResultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"myCattleCount":0,"myTotalAssets":0,"myCreditAmount":0,"myBeefAmount":0,"myCouponCount":0}
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
         * myCattleCount : 0
         * myTotalAssets : 0.0
         * myCreditAmount : 0.0
         * myBeefAmount : 0.0
         * myCouponCount : 0.0
         */

        private int myCattleCount;
        private BigDecimal myTotalAssets;
        private BigDecimal myCreditAmount;
        private BigDecimal myBeefAmount;
        private int myCouponCount;

        public int getMyCattleCount() {
            return myCattleCount;
        }

        public void setMyCattleCount(int myCattleCount) {
            this.myCattleCount = myCattleCount;
        }

        public BigDecimal getMyTotalAssets() {
            return myTotalAssets;
        }

        public void setMyTotalAssets(BigDecimal myTotalAssets) {
            this.myTotalAssets = myTotalAssets;
        }

        public BigDecimal getMyCreditAmount() {
            return myCreditAmount;
        }

        public void setMyCreditAmount(BigDecimal myCreditAmount) {
            this.myCreditAmount = myCreditAmount;
        }

        public BigDecimal getMyBeefAmount() {
            return myBeefAmount;
        }

        public void setMyBeefAmount(BigDecimal myBeefAmount) {
            this.myBeefAmount = myBeefAmount;
        }

        public int getMyCouponCount() {
            return myCouponCount;
        }

        public void setMyCouponCount(int myCouponCount) {
            this.myCouponCount = myCouponCount;
        }
    }
}
