package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 13:52
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class SafeInfoResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : {"id":13,"mobile":"18703643373","zfbName":"","zfbId":"","wxName":"","wxId":"","isPassWord":"0"}
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
         * id : 13
         * mobile : 18703643373
         * zfbName :
         * zfbId :
         * wxName :
         * wxId :
         * isPassWord : 0
         */

        private int id;
        private String mobile;
        private String zfbName;
        private String zfbId;
        private String wxName;
        private String wxId;
        private String isPassWord;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getZfbName() {
            return zfbName;
        }

        public void setZfbName(String zfbName) {
            this.zfbName = zfbName;
        }

        public String getZfbId() {
            return zfbId;
        }

        public void setZfbId(String zfbId) {
            this.zfbId = zfbId;
        }

        public String getWxName() {
            return wxName;
        }

        public void setWxName(String wxName) {
            this.wxName = wxName;
        }

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public String getIsPassWord() {
            return isPassWord;
        }

        public void setIsPassWord(String isPassWord) {
            this.isPassWord = isPassWord;
        }
    }
}
