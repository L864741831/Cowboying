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
     * code : in esse
     * bizData : [{"feedbackId":3.310573498195079E7,"content":"quis","imageList":"qui proident voluptate mollit"},{"feedbackId":3.7333558482004404E7,"content":"veniam dolore","imageList":"sed dolor"},{"feedbackId":7.408452498089156E7,"content":"est pariatur","imageList":"nulla in velit"}]
     */

    private String code;
    private String message;
    private List<BizDataBean> bizData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * feedbackId : 3.310573498195079E7
         * content : quis
         * imageList : qui proident voluptate mollit
         */

        private int feedbackId;
        private String content;
        private String imageList;


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

        public String getImageList() {
            return imageList;
        }

        public void setImageList(String imageList) {
            this.imageList = imageList;
        }
    }
}
