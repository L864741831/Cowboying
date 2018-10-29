package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/10/28 10:18
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class PastureAllResultBean {


    /**
     * code : 000000
     * message :
     * bizData : [{"pastureId":5,"name":"csdn程序猿的牧场zzz"},{"pastureId":14,"name":"csdn程序猿的牧场"}]
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
         * pastureId : 5
         * name : csdn程序猿的牧场zzz
         */

        private int pastureId;
        private String name;

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
    }
}
