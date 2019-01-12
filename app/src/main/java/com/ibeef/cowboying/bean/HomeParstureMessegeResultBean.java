package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2019/1/10 17:26
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeParstureMessegeResultBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"id":5,"name":"","title":"test资讯422","imageUrl":"images/59eb4a9c1758d68f4c8cd4f651419f7c.jpg","linkUrl":"http://www.w3school.com.cn/","createTime":1547103281000},{"id":11,"name":null,"title":"fdsa","imageUrl":"images/d7848288f18eec6082b175c3a1706157.jpg","linkUrl":"http://fsda.com","createTime":1547102224000},{"id":10,"name":null,"title":"fdsa","imageUrl":"images/d7848288f18eec6082b175c3a1706157.jpg","linkUrl":"http://fdsaf.com","createTime":1547102177000},{"id":8,"name":null,"title":"口袋牧场资讯111","imageUrl":"images/c74cb5a5ebeb2c01811ae7fe83a398b5","linkUrl":"www.baidu.com","createTime":1547086836000}]
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
         * id : 5
         * name :
         * title : test资讯422
         * imageUrl : images/59eb4a9c1758d68f4c8cd4f651419f7c.jpg
         * linkUrl : http://www.w3school.com.cn/
         * createTime : 1547103281000
         */

        private int id;
        private String name;
        private String title;
        private String imageUrl;
        private String linkUrl;
        private long createTime;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
