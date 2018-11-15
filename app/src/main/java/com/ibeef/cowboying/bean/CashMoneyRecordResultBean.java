package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/12 15:44
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CashMoneyRecordResultBean {
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

        private String orderCode;
        private String status;
        private String createTime;
        private String successTime;
        private String reason;
        private double amount;

        public String getOrderCode() {
            return orderCode;
        }

        public String getSuccessTime() {
            return successTime;
        }

        public void setSuccessTime(String successTime) {
            this.successTime = successTime;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
