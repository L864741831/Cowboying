package com.ibeef.cowboying.bean;

import java.util.List;

public class PayCodeBean {


    /**
     * code : 000000
     * message : “”
     * bizData : {"payCode":"366296152050057882","payTypeList":[{"name":"钱包余额","payType":"1","amount":0,"checked":true}],"checkedPayType":"1"}
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

    public static class BizDataBean {
        /**
         * payCode : 366296152050057882
         * payTypeList : [{"name":"钱包余额","payType":"1","amount":0,"checked":true}]
         * checkedPayType : 1
         */

        private String payCode;
        private String checkedPayType;
        private List<PayTypeListBean> payTypeList;

        public String getPayCode() {
            return payCode;
        }

        public void setPayCode(String payCode) {
            this.payCode = payCode;
        }

        public String getCheckedPayType() {
            return checkedPayType;
        }

        public void setCheckedPayType(String checkedPayType) {
            this.checkedPayType = checkedPayType;
        }

        public List<PayTypeListBean> getPayTypeList() {
            return payTypeList;
        }

        public void setPayTypeList(List<PayTypeListBean> payTypeList) {
            this.payTypeList = payTypeList;
        }

        public static class PayTypeListBean {
            /**
             * name : 钱包余额
             * payType : 1
             * amount : 0
             * checked : true
             */

            private String name;
            private String payType;
            private double amount;
            private boolean checked;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }
        }
    }
}
