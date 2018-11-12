package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/12 14:32
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class WalletRecordResultBean {
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

    public  class BizDataBean {

        private String changeType;
        private String changeTime;
        private double changeAmount;

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }

        public double getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(double changeAmount) {
            this.changeAmount = changeAmount;
        }
    }
}
