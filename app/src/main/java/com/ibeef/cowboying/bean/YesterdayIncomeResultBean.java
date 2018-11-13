package com.ibeef.cowboying.bean;

import java.math.BigDecimal;

/**
 * @author ls
 * @date on 2018/11/13 9:51
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class YesterdayIncomeResultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"cumulativeIncome":0,"adoptCattleIncome":0,"groupCattleIncome":0,"recommendIncome":0}
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
         * cumulativeIncome : 0.0
         * adoptCattleIncome : 0.0
         * groupCattleIncome : 0.0
         * recommendIncome : 0.0
         */

        private BigDecimal cumulativeIncome;
        private BigDecimal adoptCattleIncome;
        private BigDecimal groupCattleIncome;
        private BigDecimal recommendIncome;

        public BigDecimal getCumulativeIncome() {
            return cumulativeIncome;
        }

        public void setCumulativeIncome(BigDecimal cumulativeIncome) {
            this.cumulativeIncome = cumulativeIncome;
        }

        public BigDecimal getAdoptCattleIncome() {
            return adoptCattleIncome;
        }

        public void setAdoptCattleIncome(BigDecimal adoptCattleIncome) {
            this.adoptCattleIncome = adoptCattleIncome;
        }

        public BigDecimal getGroupCattleIncome() {
            return groupCattleIncome;
        }

        public void setGroupCattleIncome(BigDecimal groupCattleIncome) {
            this.groupCattleIncome = groupCattleIncome;
        }

        public BigDecimal getRecommendIncome() {
            return recommendIncome;
        }

        public void setRecommendIncome(BigDecimal recommendIncome) {
            this.recommendIncome = recommendIncome;
        }
    }
}
