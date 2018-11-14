package com.ibeef.cowboying.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ls
 * @date on 2018/11/14 14:43
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class AdoptInfosResultBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"cattleId":127,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":13,"schemeId":3},{"cattleId":128,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":14,"schemeId":3},{"cattleId":129,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":14,"schemeId":3},{"cattleId":130,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":15,"schemeId":3},{"cattleId":131,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":13,"schemeId":3},{"cattleId":133,"pastureId":35,"code":"abcd1025","imageUrl":"images/31fa1235cce759035cf3ca3996a33b56.png","age":10.2,"weight":242,"status":"0","createTime":1541648080000,"updateTime":null,"operator":"MrWang1","orderId":14,"schemeId":3}]
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
         * cattleId : 127
         * pastureId : 35
         * code : abcd1025
         * imageUrl : images/31fa1235cce759035cf3ca3996a33b56.png
         * age : 10.2
         * weight : 242.0
         * status : 0
         * createTime : 1541648080000
         * updateTime : null
         * operator : MrWang1
         * orderId : 13
         * schemeId : 3
         */

        private int cattleId;
        private int pastureId;
        private String code;
        private String imageUrl;
        private BigDecimal age;
        private BigDecimal weight;
        private String status;
        private String createTime;
        private String updateTime;
        private String operator;
        private int orderId;
        private int schemeId;

        public int getCattleId() {
            return cattleId;
        }

        public void setCattleId(int cattleId) {
            this.cattleId = cattleId;
        }

        public int getPastureId() {
            return pastureId;
        }

        public void setPastureId(int pastureId) {
            this.pastureId = pastureId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getAge() {
            return age;
        }

        public void setAge(BigDecimal age) {
            this.age = age;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(int schemeId) {
            this.schemeId = schemeId;
        }
    }
}
