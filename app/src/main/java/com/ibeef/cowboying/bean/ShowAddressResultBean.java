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
     * message :
     * bizData : [{"id":7,"name":"屠龙记","mobile":"18703643373","province":"浙江省","provinceId":330000,"city":"杭州市","cityId":330100,"region":"滨江区","regionId":330108,"detailAddress":"下午","isDefault":"1"},{"id":8,"name":"小兰","mobile":"18783643373","province":"浙江省","provinceId":330000,"city":"杭州市","cityId":330100,"region":"滨江区","regionId":330108,"detailAddress":"河南","isDefault":"0"},{"id":9,"name":"李","mobile":"18703643373","province":"浙江省","provinceId":330000,"city":"杭州市","cityId":330100,"region":"滨江区","regionId":330108,"detailAddress":"河南财经政法大学","isDefault":"0"}]
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

    public  class BizDataBean implements Serializable{
        /**
         * id : 7
         * name : 屠龙记
         * mobile : 18703643373
         * province : 浙江省
         * provinceId : 330000
         * city : 杭州市
         * cityId : 330100
         * region : 滨江区
         * regionId : 330108
         * detailAddress : 下午
         * isDefault : 1
         */

        private int id;
        private String name;
        private String mobile;
        private String province;
        private int provinceId;
        private String city;
        private int cityId;
        private String region;
        private int regionId;
        private String detailAddress;
        private String isDefault;

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

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }
    }
}
