package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/28 10:21
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PastureDetelResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : {"id":5,"name":"csdn程序猿的牧场zzz","mainImageUrl":"tryout/images/31fa1235cce759035cf3ca3996a33b55.png","videoPlayUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1541147380&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=iIW67U%2BOhCOI2pO3zQOiLPqeLgA%3D","videoCoverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1541147380&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=0vFgiaM%2FyKpwVFjVtOKAnTraUNQ%3D","liveUrl":"www.douyu.com","describes":"好多牛啊","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","bbbbbbbb"]}
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
         * id : 5
         * name : csdn程序猿的牧场zzz
         * mainImageUrl : tryout/images/31fa1235cce759035cf3ca3996a33b55.png
         * videoPlayUrl : https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/d866c1cbdf8a4ff0a5f934c18b3a2bff-e5b9dc041e7eb8ebee52eb9682ac7a62-ld.mp4?Expires=1541147380&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=iIW67U%2BOhCOI2pO3zQOiLPqeLgA%3D
         * videoCoverUrl : http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1541147380&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=0vFgiaM%2FyKpwVFjVtOKAnTraUNQ%3D
         * liveUrl : www.douyu.com
         * describes : 好多牛啊
         * imageList : ["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","bbbbbbbb"]
         */

        private int id;
        private String name;
        private String mainImageUrl;
        private String videoPlayUrl;
        private String videoCoverUrl;
        private String livePlayUrl;
        private String describes;
        private String liveCoverUrl;
        private List<String> imageList;

        public String getLiveCoverUrl() {
            return liveCoverUrl;
        }

        public void setLiveCoverUrl(String liveCoverUrl) {
            this.liveCoverUrl = liveCoverUrl;
        }

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

        public String getMainImageUrl() {
            return mainImageUrl;
        }

        public void setMainImageUrl(String mainImageUrl) {
            this.mainImageUrl = mainImageUrl;
        }

        public String getVideoPlayUrl() {
            return videoPlayUrl;
        }

        public void setVideoPlayUrl(String videoPlayUrl) {
            this.videoPlayUrl = videoPlayUrl;
        }

        public String getVideoCoverUrl() {
            return videoCoverUrl;
        }

        public void setVideoCoverUrl(String videoCoverUrl) {
            this.videoCoverUrl = videoCoverUrl;
        }

        public String getLivePlayUrl() {
            return livePlayUrl;
        }

        public void setLivePlayUrl(String livePlayUrl) {
            this.livePlayUrl = livePlayUrl;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }
    }
}
