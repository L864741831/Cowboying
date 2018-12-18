package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ls
 * @date on 2018/10/28 10:12
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeBannerResultBean implements Serializable {

    /**
     * code : 000000
     * message : null
     * bizData : {"topBannerList":[{"id":2,"position":"1","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"},{"id":3,"position":"1","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}],"middleBannerList":[{"id":4,"position":"2","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"},{"id":5,"position":"2","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}],"popBannerResDto":{"id":7,"position":"4","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}}
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
         * topBannerList : [{"id":2,"position":"1","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"},{"id":3,"position":"1","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}]
         * middleBannerList : [{"id":4,"position":"2","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"},{"id":5,"position":"2","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}]
         * popBannerResDto : {"id":7,"position":"4","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}
         */

        private PopBannerResDtoBean popBanner;
        private NewUserActivity newUserActivity;
        private List<TopBannerListBean> topBannerList;
        private List<MiddleBannerListBean> middleBannerList;

        public PopBannerResDtoBean getPopBanner() {
            return popBanner;
        }

        public void setPopBanner(PopBannerResDtoBean popBanner) {
            this.popBanner = popBanner;
        }

        public NewUserActivity getNewUserActivity() {
            return newUserActivity;
        }

        public void setNewUserActivity(NewUserActivity newUserActivity) {
            this.newUserActivity = newUserActivity;
        }

        public List<TopBannerListBean> getTopBannerList() {
            return topBannerList;
        }

        public void setTopBannerList(List<TopBannerListBean> topBannerList) {
            this.topBannerList = topBannerList;
        }

        public List<MiddleBannerListBean> getMiddleBannerList() {
            return middleBannerList;
        }

        public void setMiddleBannerList(List<MiddleBannerListBean> middleBannerList) {
            this.middleBannerList = middleBannerList;
        }

        public class  NewUserActivity implements  Serializable{

            private String homeBanner;
            private String describe;
            private Integer schemeId;

            public Integer getSchemeId() {
                return schemeId;
            }

            public void setSchemeId(Integer schemeId) {
                this.schemeId = schemeId;
            }

            public String getHomeBanner() {
                return homeBanner;
            }

            public void setHomeBanner(String homeBanner) {
                this.homeBanner = homeBanner;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }
        }
        public  class PopBannerResDtoBean implements Serializable{
            /**
             * id : 7
             * position : 4
             * imageUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             * pageId : null
             * linkUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             */

            private int id;
            private String position;
            private String imageUrl;
            private Object pageId;
            private String linkUrl;
            private String pageUrl;
            private String params;

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public String getPageUrl() {
                return pageUrl;
            }

            public void setPageUrl(String pageUrl) {
                this.pageUrl = pageUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public Object getPageId() {
                return pageId;
            }

            public void setPageId(Object pageId) {
                this.pageId = pageId;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }
        }

        public  class TopBannerListBean implements Serializable{
            /**
             * id : 2
             * position : 1
             * imageUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             * pageId : null
             * linkUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             */

            private int id;
            private String position;
            private String imageUrl;
            private Object pageId;
            private String linkUrl;
            private String pageUrl;
            private String params;

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public String getPageUrl() {
                return pageUrl;
            }

            public void setPageUrl(String pageUrl) {
                this.pageUrl = pageUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public Object getPageId() {
                return pageId;
            }

            public void setPageId(Object pageId) {
                this.pageId = pageId;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }
        }

        public  class MiddleBannerListBean implements Serializable{
            /**
             * id : 4
             * position : 2
             * imageUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             * pageId : null
             * linkUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
             */

            private int id;
            private String position;
            private String imageUrl;
            private Object pageId;
            private String linkUrl;
            private String params;

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public Object getPageId() {
                return pageId;
            }

            public void setPageId(Object pageId) {
                this.pageId = pageId;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }
        }
    }
}
