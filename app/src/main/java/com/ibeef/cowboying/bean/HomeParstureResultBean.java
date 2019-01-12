package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2019/1/10 17:05
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class HomeParstureResultBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"pastureId":35,"name":"撒","pastureImage":"images/2ff889ef4d454c15a237d43c144c00ae.jpg","introduction":""},{"pastureId":37,"name":"fsdafs","pastureImage":"images/36f0014d1cab45fcaeafd88acedfabd4.jpg","introduction":null},{"pastureId":38,"name":"fdsaf","pastureImage":"images/a6745f419e7e4341b0145692fc0dadee.jpg","introduction":null}]
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
         * pastureId : 35
         * name : 撒
         * pastureImage : images/2ff889ef4d454c15a237d43c144c00ae.jpg
         * introduction :
         */

        private int pastureId;
        private String name;
        private String pastureImage;
        private String introduction;

        public int getPastureId() {
            return pastureId;
        }

        public void setPastureId(int pastureId) {
            this.pastureId = pastureId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPastureImage() {
            return pastureImage;
        }

        public void setPastureImage(String pastureImage) {
            this.pastureImage = pastureImage;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
