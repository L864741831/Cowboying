package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/30 9:53
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CheckVersionBean {

    /**
     * code : 000000
     * message :
     * bizData : {"upgrade":"0","must":"","version":"","fileUrl":""}
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
         * upgrade : 0
         * must :
         * version :
         * fileUrl :
         */

        private String upgrade;
        private String must;
        private String version;
        private String fileUrl;

        public String getUpgrade() {
            return upgrade;
        }

        public void setUpgrade(String upgrade) {
            this.upgrade = upgrade;
        }

        public String getMust() {
            return must;
        }

        public void setMust(String must) {
            this.must = must;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
