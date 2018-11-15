package com.ibeef.cowboying.bean;

import java.math.BigDecimal;
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
    private int pageNo;
    private Object pageSize;
    private Object totalRows;
    private Object totalPages;
    private List<BizDataBean> bizData;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Object getPageSize() {
        return pageSize;
    }

    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
    }

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
        this.totalPages = totalPages;
    }

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
        private BigDecimal changeAmount;

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

        public BigDecimal getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(BigDecimal changeAmount) {
            this.changeAmount = changeAmount;
        }
    }
}
