package com.ibeef.cowboying.bean;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/12 14:17
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class IncomeInfoResultBean {


    /**
     * code : 000000
     * message :
     * bizData : {"totalAssets":0,"yesterdayIncome":0,"cumulativeIncome":0,"nearSevenDaysIncome":[{"date":"2018-11-12","amount":0},{"date":"2018-11-11","amount":0},{"date":"2018-11-10","amount":0},{"date":"2018-11-09","amount":0},{"date":"2018-11-08","amount":0},{"date":"2018-11-07","amount":0},{"date":"2018-11-06","amount":0}],"walletBalance":0,"cridetBalance":0}
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
         * totalAssets : 0.0
         * yesterdayIncome : 0.0
         * cumulativeIncome : 0.0
         * nearSevenDaysIncome : [{"date":"2018-11-12","amount":0},{"date":"2018-11-11","amount":0},{"date":"2018-11-10","amount":0},{"date":"2018-11-09","amount":0},{"date":"2018-11-08","amount":0},{"date":"2018-11-07","amount":0},{"date":"2018-11-06","amount":0}]
         * walletBalance : 0.0
         * cridetBalance : 0.0
         */

        private BigDecimal totalAssets;
        private BigDecimal yesterdayIncome;
        private BigDecimal cumulativeIncome;
        private BigDecimal walletBalance;
        private BigDecimal cridetBalance;
        private List<NearSevenDaysIncomeBean> nearSevenDaysIncome;


        public BigDecimal getTotalAssets() {
            return totalAssets;
        }

        public void setTotalAssets(BigDecimal totalAssets) {
            this.totalAssets = totalAssets;
        }

        public BigDecimal getYesterdayIncome() {
            return yesterdayIncome;
        }

        public void setYesterdayIncome(BigDecimal yesterdayIncome) {
            this.yesterdayIncome = yesterdayIncome;
        }

        public BigDecimal getCumulativeIncome() {
            return cumulativeIncome;
        }

        public void setCumulativeIncome(BigDecimal cumulativeIncome) {
            this.cumulativeIncome = cumulativeIncome;
        }

        public BigDecimal getWalletBalance() {
            return walletBalance;
        }

        public void setWalletBalance(BigDecimal walletBalance) {
            this.walletBalance = walletBalance;
        }

        public BigDecimal getCridetBalance() {
            return cridetBalance;
        }

        public void setCridetBalance(BigDecimal cridetBalance) {
            this.cridetBalance = cridetBalance;
        }

        public List<NearSevenDaysIncomeBean> getNearSevenDaysIncome() {
            return nearSevenDaysIncome;
        }

        public void setNearSevenDaysIncome(List<NearSevenDaysIncomeBean> nearSevenDaysIncome) {
            this.nearSevenDaysIncome = nearSevenDaysIncome;
        }

        public  class NearSevenDaysIncomeBean {
            /**
             * date : 2018-11-12
             * amount : 0.0
             */

            private String date;
            private BigDecimal amount;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public BigDecimal getAmount() {
                return amount;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }
        }
    }
}
