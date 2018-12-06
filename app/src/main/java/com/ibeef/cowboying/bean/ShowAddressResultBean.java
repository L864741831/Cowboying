package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 18:11
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ShowAddressResultBean implements Serializable {


    /**
     * code : 000000
     * message : “”
     * bizData : [{"id":3,"name":"李小明","mobile":"18703643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"长江路28号","isDefualt":null},{"id":4,"name":"李小明","mobile":"18703643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"长江路28号","isDefualt":null},{"id":5,"name":"李小明","mobile":"18703643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"长江路28号","isDefualt":null},{"id":6,"name":"李小明","mobile":"18703643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"长江路28号","isDefualt":null},{"id":7,"name":"李小明","mobile":"18703643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"长江路28号","isDefualt":null},{"id":8,"name":"小兰","mobile":"18783643373","provinceId":null,"cityId":null,"regionId":null,"detailAddress":"河南","isDefualt":null}]
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

    public  class BizDataBean implements Serializable{
        /**
         * id : 3
         * name : 李小明
         * mobile : 18703643373
         * provinceId : null
         * cityId : null
         * regionId : null
         * detailAddress : 长江路28号
         * isDefualt : null
         */

        private int id;
        private String name;
        private String mobile;
        private String province;
        private String city;
        private String region;
        private String detailAddress;
        private Object isDefualt;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public Object getIsDefualt() {
            return isDefualt;
        }

        public void setIsDefualt(Object isDefualt) {
            this.isDefualt = isDefualt;
        }
    }
}
