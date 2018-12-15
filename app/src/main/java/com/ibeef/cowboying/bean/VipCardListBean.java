package com.ibeef.cowboying.bean;

import java.util.List;

public class VipCardListBean {


    /**
     * code : 000000
     * message :
     * bizData : [{"amount":1100,"changeType":"1","recordSn":"201812134030580929343782912","amountResult":1100,"remarks":"会员充值","createTime":1544687679000},{"amount":1000,"changeType":"1","recordSn":"201812134031566091762270209","amountResult":2100,"remarks":"会员充值","createTime":1544687692000},{"amount":1000,"changeType":"1","recordSn":"201812134033114204134178818","amountResult":3100,"remarks":"会员充值","createTime":1544687714000},{"amount":-1000,"changeType":"1","recordSn":"201812134126352790169583616","amountResult":2100,"remarks":"会员充值","createTime":1544689039000},{"amount":-1000,"changeType":"2","recordSn":"201812134133811877052416000","amountResult":1100,"remarks":"充值撤销","createTime":1544689146000}]
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

    public static class BizDataBean {
        /**
         * amount : 1100
         * changeType : 1
         * recordSn : 201812134030580929343782912
         * amountResult : 1100
         * remarks : 会员充值
         * createTime : 1544687679000
         */

        private double amount;
        private String changeType;
        private String recordSn;
        private double amountResult;
        private String remarks;
        private long createTime;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getRecordSn() {
            return recordSn;
        }

        public void setRecordSn(String recordSn) {
            this.recordSn = recordSn;
        }

        public double getAmountResult() {
            return amountResult;
        }

        public void setAmountResult(double amountResult) {
            this.amountResult = amountResult;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
