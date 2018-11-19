package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.math.BigDecimal;

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
        private int totalSalesQuantity;
        private int totalUserQuantity;

        public int getTotalSalesQuantity() {
            return totalSalesQuantity;
        }

        public void setTotalSalesQuantity(int totalSalesQuantity) {
            this.totalSalesQuantity = totalSalesQuantity;
        }

        public int getTotalUserQuantity() {
            return totalUserQuantity;
        }

        public void setTotalUserQuantity(int totalUserQuantity) {
            this.totalUserQuantity = totalUserQuantity;
        }
    }
}
