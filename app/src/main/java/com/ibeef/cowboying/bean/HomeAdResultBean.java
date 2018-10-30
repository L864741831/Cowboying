package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/27 17:57
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeAdResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : {"id":6,"position":"3","imageUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png","pageId":null,"linkUrl":"tryout/images/ce38e911e896a4843e2d2360387023c6.png"}
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
         * id : 6
         * position : 3
         * imageUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
         * pageId : null
         * linkUrl : tryout/images/ce38e911e896a4843e2d2360387023c6.png
         */

        private int id;
        private String position;
        private String imageUrl;
        private Object pageId;
        private String linkUrl;

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
