package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/19 15:51
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MessegeListReslutBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"id":20,"status":"0","type":"3","title":"订单支付成功","imageUrl":null,"pageUrl":"adop_order_detail","pageName":"买牛订单详情","params":"258","createTime":1545199636000,"content":"尊敬的牧场主，您已成功支付5000.00元，即可为您进行审核，请耐心等待，有疑问请联系客服"}]
     * pageNo : 1
     * pageSize : null
     * totalRows : null
     * totalPages : null
     */

    private String code;
    private String message;
    private int pageNo;
    private Object pageSize;
    private Object totalRows;
    private Object totalPages;
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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Object getPageSize() {
        return pageSize;
    }

    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
    }

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
        this.totalPages = totalPages;
    }

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * id : 20
         * status : 0
         * type : 3
         * title : 订单支付成功
         * imageUrl : null
         * pageUrl : adop_order_detail
         * pageName : 买牛订单详情
         * params : 258
         * createTime : 1545199636000
         * content : 尊敬的牧场主，您已成功支付5000.00元，即可为您进行审核，请耐心等待，有疑问请联系客服
         */

        private int id;
        private String status;
        private String type;
        private String title;
        private String imageUrl;
        private String pageUrl;
        private String pageName;
        private String params;
        private long createTime;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getPageName() {
            return pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
