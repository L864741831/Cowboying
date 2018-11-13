package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/13 14:35
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CashMoneyUserInfoResultBean {


    /**
     * code : 000000
     * message :
     * bizData : {"bindAlipay":"0","nickName":null}
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
         * bindAlipay : 0
         * nickName : null
         */

        private String bindAlipay;
        private String nickName;

        public String getBindAlipay() {
            return bindAlipay;
        }

        public void setBindAlipay(String bindAlipay) {
            this.bindAlipay = bindAlipay;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
