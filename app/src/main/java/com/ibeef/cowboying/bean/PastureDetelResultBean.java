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
     * bizData : {"id":5,"name":"csdn程序猿的牧场zzz","mainImageUrl":"tryout/images/31fa1235cce759035cf3ca3996a33b55.png","videoPlayUrl":null,"videoCoverUrl":null,"liveUrl":"www.douyu.com","describes":"好多牛啊","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","bbbbbbbb"]}
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
         * videoPlayUrl : null
         * videoCoverUrl : null
         * liveUrl : www.douyu.com
         * describes : 好多牛啊
         * imageList : ["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png","bbbbbbbb"]
         */

        private int id;
        private String name;
        private String mainImageUrl;
        private String videoPlayUrl;
        private String videoCoverUrl;
        private String liveUrl;
        private String describes;
        private List<String> imageList;

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

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            this.liveUrl = liveUrl;
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
