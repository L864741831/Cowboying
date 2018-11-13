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
     * message :
     * bizData : {"id":25,"mobile":"13673360652","zfbName":"","zfbId":16,"wxName":"","wxId":0,"isPassWord":"1"}
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
         * id : 25
         * mobile : 13673360652
         * zfbName :
         * zfbId : 16
         * wxName :
         * wxId : 0
         * isPassWord : 1
         */

        private Integer id;
        private String mobile;
        private String zfbName;
        private Integer zfbId;
        private String wxName;
        private Integer wxId;
        private String isPassWord;
        private String isPayPassWord;

        public String getIsPayPassWord() {
            return isPayPassWord;
        }

        public void setIsPayPassWord(String isPayPassWord) {
            this.isPayPassWord = isPayPassWord;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getZfbId() {
            return zfbId;
        }

        public void setZfbId(Integer zfbId) {
            this.zfbId = zfbId;
        }

        public Integer getWxId() {
            return wxId;
        }

        public void setWxId(Integer wxId) {
            this.wxId = wxId;
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



        public String getWxName() {
            return wxName;
        }

        public void setWxName(String wxName) {
            this.wxName = wxName;
        }



        public String getIsPassWord() {
            return isPassWord;
        }

        public void setIsPassWord(String isPassWord) {
            this.isPassWord = isPassWord;
        }
    }
}
