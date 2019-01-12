package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/17 16:00
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeSellCowNumResultBean {
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
        private String totalSalesQuantity;
        private String totalUserQuantity;
        private boolean showStatistic;

        public boolean isShowStatistic() {
            return showStatistic;
        }

        public void setShowStatistic(boolean showStatistic) {
            this.showStatistic = showStatistic;
        }

        public String getTotalSalesQuantity() {
            return totalSalesQuantity;
        }

        public void setTotalSalesQuantity(String totalSalesQuantity) {
            this.totalSalesQuantity = totalSalesQuantity;
        }

        public String getTotalUserQuantity() {
            return totalUserQuantity;
        }

        public void setTotalUserQuantity(String totalUserQuantity) {
            this.totalUserQuantity = totalUserQuantity;
        }
    }
}
