package com.ibeef.cowboying.bean;

import java.math.BigDecimal;

/**
 * @author ls
 * @date on 2018/11/15 10:33
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SellCowsResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : {"earn":0,"orderId":63,"pastureNum":1,"rate":9,"price":0.01,"debt":0,"brokerage":0}
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
         * earn : 0.0
         * orderId : 63
         * pastureNum : 1
         * rate : 9.0
         * price : 0.01
         * debt : 0
         * brokerage : 0
         */

        private BigDecimal earn;
        private int orderId;
        private int pastureNum;
        private BigDecimal rate;
        private BigDecimal price;
        private BigDecimal debt;
        private BigDecimal brokerage;


        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getPastureNum() {
            return pastureNum;
        }

        public void setPastureNum(int pastureNum) {
            this.pastureNum = pastureNum;
        }

        public BigDecimal getEarn() {
            return earn;
        }

        public void setEarn(BigDecimal earn) {
            this.earn = earn;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getDebt() {
            return debt;
        }

        public void setDebt(BigDecimal debt) {
            this.debt = debt;
        }

        public BigDecimal getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(BigDecimal brokerage) {
            this.brokerage = brokerage;
        }
    }
}
