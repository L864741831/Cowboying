package com.ibeef.cowboying.base;

/**
 * @Author $ls
 * @Date 2018/5/10 10:19
 * @Description
 */

public class MdUploadImgBean {

    /**
     * status : 000000000
     * message :
     * data : {"fileName":"tryout/images/d3430faa9a5f4df7a924f28f41408055.jpg","filePath":"http://lgf8953.oss-cn-beijing.aliyuncs.com/tryout/images/d3430faa9a5f4df7a924f28f41408055.jpg"}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {
        /**
         * fileName : tryout/images/d3430faa9a5f4df7a924f28f41408055.jpg
         * filePath : http://lgf8953.oss-cn-beijing.aliyuncs.com/tryout/images/d3430faa9a5f4df7a924f28f41408055.jpg
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
