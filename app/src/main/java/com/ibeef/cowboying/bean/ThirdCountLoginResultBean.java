package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 15:43
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ThirdCountLoginResultBean {


    /**
     * code : 000000
     * message :
     * bizData : {"token":"","visitorId":2,"mobile":""}
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
         * token :
         * visitorId : 2
         * mobile :
         */

        private String token;
        private int visitorId;
        private String mobile;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(int visitorId) {
            this.visitorId = visitorId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
