package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/21 18:18
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class VideoAppkeyResultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"appKey":"6db46fefb0534ce494d1e148bccc7ec8","accessToken":"at.dxrq1dwfci6vdt5s4dbsdzwidje9ehf4-64bi9xpoba-1ed6cr7-8avwhtfe2","expireTime":1545968348246}
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
         * appKey : 6db46fefb0534ce494d1e148bccc7ec8
         * accessToken : at.dxrq1dwfci6vdt5s4dbsdzwidje9ehf4-64bi9xpoba-1ed6cr7-8avwhtfe2
         * expireTime : 1545968348246
         */

        private String appKey;
        private String accessToken;
        private long expireTime;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }
    }
}
