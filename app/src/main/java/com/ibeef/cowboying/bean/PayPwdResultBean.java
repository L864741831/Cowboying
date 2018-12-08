package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/7 15:40
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PayPwdResultBean {


    /**
     * code : 000000
     * message :
     * bizData : {"havePayPassword":false,"mobile":"18503876318"}
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
         * havePayPassword : false
         * mobile : 18503876318
         */

        private boolean havePayPassword;
        private String mobile;

        public boolean isHavePayPassword() {
            return havePayPassword;
        }

        public void setHavePayPassword(boolean havePayPassword) {
            this.havePayPassword = havePayPassword;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
