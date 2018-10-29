package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/27 18:34
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeVideoResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : [{"id":1,"name":"殷俊杰的视频","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=oWuWhwrqd9SZYTEezoSwrfX6BMY%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=vDbXirILTpUC3I3UPJrGVWkpBVo%3D"},{"id":2,"name":"澳洲肥牛","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=oWuWhwrqd9SZYTEezoSwrfX6BMY%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=vDbXirILTpUC3I3UPJrGVWkpBVo%3D"},{"id":7,"name":"澳洲肥牛2","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=oWuWhwrqd9SZYTEezoSwrfX6BMY%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=vDbXirILTpUC3I3UPJrGVWkpBVo%3D"},{"id":8,"name":"澳洲肥牛2","coverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=oWuWhwrqd9SZYTEezoSwrfX6BMY%3D","playUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=vDbXirILTpUC3I3UPJrGVWkpBVo%3D"}]
     */

    private String code;
    private String message;
    private List<BizDataBean> bizData;

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
        /**
         * id : 1
         * name : 殷俊杰的视频
         * coverUrl : http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=oWuWhwrqd9SZYTEezoSwrfX6BMY%3D
         * playUrl : https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1540804314&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=vDbXirILTpUC3I3UPJrGVWkpBVo%3D
         */

        private int id;
        private String name;
        private String coverUrl;
        private String playUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }
    }
}
