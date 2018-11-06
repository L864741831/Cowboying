package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/3 18:37
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class OssResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : {"AccessKeyId":"STS.NJm35KmWyTQcovbQuhcqwwsE2","AccessKeySecret":"738mxehtxKpmYABMwR67UARjsCzwK91CyuY186mapEhV","SecurityToken":"CAISlQN1q6Ft5B2yfSjIr4nYeI//gIhY45OIbVDTtXU9b/5bmLbu0Dz2IHpJeHZtAO8cvvQwmGBX7vgelrh+W4NIX0rNaY5t9ZlN9wqkbtJAAVJSa/hW5qe+EE2/VjRAta27OpdcJbGwU/OpbE++fEYRpZLxaTSlWXG8LJSNkuQJR98LXw6+HzgkYdBNPVlNpdM9P3ncPurPVxnxmTjoEVZPsAhxgn8dkaOk2Z+w5x7TkFDdwO0YrJiTR5+/dJtIPYxja96vwPcUBq3ay3x54R9Q6IJriLBF4T7douyFB15Y73fhNPHPoPluNxNebKo3ELJf1q+e88d1oevOjY/65g9QNOVOKUTlSZun3dHPFZHiVJ8wfq2pPG/WgJLddMvtuhg4fGgWcQhHfcYsMDhxDBUjRyr4Uvb8oQqaOlz5G/HZjPFnicZPog+2rYbQFT+mWK6E1CsUAJg4Yn4zOgQetW6bKfVbL1MRKgw9V+bKEdUsNE4Cs9DhuQzDSytn1WpLuP7zYfzbvK0FYJn4RIhB1YcNxCOucc79jT4agAFxywl2Tue2Ns6gnhxRWXTdkSDBY2O7OC4W5hD7/UVhUuHVjvt+IADdrNghLVO3yZ7SLSiHnnpVha5losvWIadkk8jU13CcFIGnbRzEVDdgOPUfnEUfaXM5en6WtrRk1yYYHfafavltxnl/cjO9OXUbiHjK1lc17geS942XbxH/6Q==","Expiration":"2018-11-05T03:49:49Z","Endpoint":"http://oss-cn-beijing.aliyuncs.com","BucketName":"pasture-center","Folder":"images/"}
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
         * AccessKeyId : STS.NJm35KmWyTQcovbQuhcqwwsE2
         * AccessKeySecret : 738mxehtxKpmYABMwR67UARjsCzwK91CyuY186mapEhV
         * SecurityToken : CAISlQN1q6Ft5B2yfSjIr4nYeI//gIhY45OIbVDTtXU9b/5bmLbu0Dz2IHpJeHZtAO8cvvQwmGBX7vgelrh+W4NIX0rNaY5t9ZlN9wqkbtJAAVJSa/hW5qe+EE2/VjRAta27OpdcJbGwU/OpbE++fEYRpZLxaTSlWXG8LJSNkuQJR98LXw6+HzgkYdBNPVlNpdM9P3ncPurPVxnxmTjoEVZPsAhxgn8dkaOk2Z+w5x7TkFDdwO0YrJiTR5+/dJtIPYxja96vwPcUBq3ay3x54R9Q6IJriLBF4T7douyFB15Y73fhNPHPoPluNxNebKo3ELJf1q+e88d1oevOjY/65g9QNOVOKUTlSZun3dHPFZHiVJ8wfq2pPG/WgJLddMvtuhg4fGgWcQhHfcYsMDhxDBUjRyr4Uvb8oQqaOlz5G/HZjPFnicZPog+2rYbQFT+mWK6E1CsUAJg4Yn4zOgQetW6bKfVbL1MRKgw9V+bKEdUsNE4Cs9DhuQzDSytn1WpLuP7zYfzbvK0FYJn4RIhB1YcNxCOucc79jT4agAFxywl2Tue2Ns6gnhxRWXTdkSDBY2O7OC4W5hD7/UVhUuHVjvt+IADdrNghLVO3yZ7SLSiHnnpVha5losvWIadkk8jU13CcFIGnbRzEVDdgOPUfnEUfaXM5en6WtrRk1yYYHfafavltxnl/cjO9OXUbiHjK1lc17geS942XbxH/6Q==
         * Expiration : 2018-11-05T03:49:49Z
         * Endpoint : http://oss-cn-beijing.aliyuncs.com
         * BucketName : pasture-center
         * Folder : images/
         */

        private String AccessKeyId;
        private String AccessKeySecret;
        private String SecurityToken;
        private String Expiration;
        private String Endpoint;
        private String BucketName;
        private String Folder;

        public String getAccessKeyId() {
            return AccessKeyId;
        }

        public void setAccessKeyId(String AccessKeyId) {
            this.AccessKeyId = AccessKeyId;
        }

        public String getAccessKeySecret() {
            return AccessKeySecret;
        }

        public void setAccessKeySecret(String AccessKeySecret) {
            this.AccessKeySecret = AccessKeySecret;
        }

        public String getSecurityToken() {
            return SecurityToken;
        }

        public void setSecurityToken(String SecurityToken) {
            this.SecurityToken = SecurityToken;
        }

        public String getExpiration() {
            return Expiration;
        }

        public void setExpiration(String Expiration) {
            this.Expiration = Expiration;
        }

        public String getEndpoint() {
            return Endpoint;
        }

        public void setEndpoint(String Endpoint) {
            this.Endpoint = Endpoint;
        }

        public String getBucketName() {
            return BucketName;
        }

        public void setBucketName(String BucketName) {
            this.BucketName = BucketName;
        }

        public String getFolder() {
            return Folder;
        }

        public void setFolder(String Folder) {
            this.Folder = Folder;
        }
    }
}
