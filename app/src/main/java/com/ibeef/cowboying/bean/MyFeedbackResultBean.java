package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/28 10:40
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyFeedbackResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"feedbackId":4,"content":"啊啊啊啊","type":null,"replyContent":"asdsfdfdgg","createTime":"2018-10-25","replyTime":"2018-10-25","status":"1","imageList":[]},{"feedbackId":5,"content":"啊啊啊啊","type":null,"replyContent":"1","createTime":"2018-10-25","replyTime":"2018-10-25","status":"1","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png"]},{"feedbackId":6,"content":"啊啊啊啊","type":null,"replyContent":"测试的回复啊啊啊啊啊啊啊","createTime":"2018-10-25","replyTime":"2018-10-25","status":"1","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png"]},{"feedbackId":11,"content":"测试","type":null,"replyContent":"sasasas","createTime":"2018-10-27","replyTime":"2018-10-27","status":"1","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png"]},{"feedbackId":12,"content":"测试111","type":null,"replyContent":"1","createTime":"2018-10-27","replyTime":"2018-10-27","status":"1","imageList":["tryout/images/31fa1235cce759035cf3ca3996a33b55.png","tryout/images/31fa1235cce759035cf3ca3996a33b55.png"]},{"feedbackId":23,"content":"顶你","type":null,"replyContent":"1","createTime":"2018-10-31","replyTime":"2018-10-31","status":"1","imageList":["/storage/emulated/0/DCIM/Camera/IMG_20181028_221853.jpg"]},{"feedbackId":24,"content":"顶你","type":null,"replyContent":"1","createTime":"2018-10-31","replyTime":"2018-10-31","status":"1","imageList":["/storage/emulated/0/DCIM/Camera/IMG_20181028_221853.jpg"]}]
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
         * feedbackId : 4
         * content : 啊啊啊啊
         * type : null
         * replyContent : asdsfdfdgg
         * createTime : 2018-10-25
         * replyTime : 2018-10-25
         * status : 1
         * imageList : []
         */

        private int feedbackId;
        private String content;
        private Object type;
        private String replyContent;
        private String createTime;
        private String replyTime;
        private String status;
        private List<String> imageList;

        public int getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(int feedbackId) {
            this.feedbackId = feedbackId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }
    }
}
