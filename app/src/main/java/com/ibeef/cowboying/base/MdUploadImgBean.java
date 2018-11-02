package com.ibeef.cowboying.base;

import java.util.List;

/**
 * @Author $ls
 * @Date 2018/5/10 10:19
 * @Description
 */

public class MdUploadImgBean {


    /**
     * code : 000000
     * message : null
     * bizData : [{"fileName":"tryout/images/012992fdbef6265ab0663ef3b47fbf89.jpg","filePath":"http://pasture.oss-cn-beijing.aliyuncs.com/tryout/images/012992fdbef6265ab0663ef3b47fbf89.jpg"}]
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
         * fileName : tryout/images/012992fdbef6265ab0663ef3b47fbf89.jpg
         * filePath : http://pasture.oss-cn-beijing.aliyuncs.com/tryout/images/012992fdbef6265ab0663ef3b47fbf89.jpg
         */

        private String fileName;
        private String filePath;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
