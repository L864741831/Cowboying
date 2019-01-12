package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ls
 * @date on 2019/1/7 16:05
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class OfflineStoreInfoResultBean implements Serializable {

    /**
     * code : 000000
     * message : null
     * bizData : {"storeId":1,"name":"Pocket Pasture","address":"河南省郑州市金水区建业凯旋广场三楼","status":"1","imageUrl":"images/c74cb5a5ebeb2c01811ae7fe83a398b5","pageUrl":"https://www.baidu.com","params":"20","mobile":"13253549937","videoCoverUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/833083be290b4526b9ec8980bfbbca81/covers/da0c1e4bcb674f63b1589f9b5e2a253f-00001.jpg?Expires=1546852338&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=eexj2iy5y2q3fpnaaU05GUXN3OY%3D","videoPlayUrl":"https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/833083be290b4526b9ec8980bfbbca81/9b5cd95d9fda48338e69e4e0a55b5f5b-ee82e0a9be8bd1d0c9d79002b955d88c-ld.mp4?Expires=1546852338&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=y2E3OuQm4%2BIwL7lKEcMw8UP5hog%3D","officeTime":["星期一 8:00-18:00","星期二 8:00-18:00","星期三 8:00-18:00","星期四 8:00-18:00","星期五 8:00-18:00","星期六 8:00-18:00","星期天 8:00-18:00"],"storeImages":["images/c74cb5a5ebeb2c01811ae7fe83a398b5"],"describes":"线下门店"}
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

    public  class BizDataBean implements Serializable{
        /**
         * storeId : 1
         * name : Pocket Pasture
         * address : 河南省郑州市金水区建业凯旋广场三楼
         * status : 1
         * imageUrl : images/c74cb5a5ebeb2c01811ae7fe83a398b5
         * pageUrl : https://www.baidu.com
         * params : 20
         * mobile : 13253549937
         * videoCoverUrl : http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/833083be290b4526b9ec8980bfbbca81/covers/da0c1e4bcb674f63b1589f9b5e2a253f-00001.jpg?Expires=1546852338&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=eexj2iy5y2q3fpnaaU05GUXN3OY%3D
         * videoPlayUrl : https://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/833083be290b4526b9ec8980bfbbca81/9b5cd95d9fda48338e69e4e0a55b5f5b-ee82e0a9be8bd1d0c9d79002b955d88c-ld.mp4?Expires=1546852338&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=y2E3OuQm4%2BIwL7lKEcMw8UP5hog%3D
         * officeTime : ["星期一 8:00-18:00","星期二 8:00-18:00","星期三 8:00-18:00","星期四 8:00-18:00","星期五 8:00-18:00","星期六 8:00-18:00","星期天 8:00-18:00"]
         * storeImages : ["images/c74cb5a5ebeb2c01811ae7fe83a398b5"]
         * describes : 线下门店
         */

        private int storeId;
        private String name;
        private String address;
        private String status;
        private String imageUrl;
        private String pageUrl;
        private String params;
        private List<String> mobile;
        private String videoCoverUrl;
        private String videoPlayUrl;
        private String describes;
        private List<String> officeTime;
        private List<String> storeImages;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public List<String> getMobile() {
            return mobile;
        }

        public void setMobile(List<String> mobile) {
            this.mobile = mobile;
        }

        public String getVideoCoverUrl() {
            return videoCoverUrl;
        }

        public void setVideoCoverUrl(String videoCoverUrl) {
            this.videoCoverUrl = videoCoverUrl;
        }

        public String getVideoPlayUrl() {
            return videoPlayUrl;
        }

        public void setVideoPlayUrl(String videoPlayUrl) {
            this.videoPlayUrl = videoPlayUrl;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public List<String> getOfficeTime() {
            return officeTime;
        }

        public void setOfficeTime(List<String> officeTime) {
            this.officeTime = officeTime;
        }

        public List<String> getStoreImages() {
            return storeImages;
        }

        public void setStoreImages(List<String> storeImages) {
            this.storeImages = storeImages;
        }
    }
}
