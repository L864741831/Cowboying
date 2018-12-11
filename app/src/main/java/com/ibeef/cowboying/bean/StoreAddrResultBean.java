package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/6 19:45
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class StoreAddrResultBean {


    /**
     * code : 000000
     * message :
     * bizData : [{"storeId":1,"name":"口袋牧场门店","address":"河南省郑州市二七区京莎B座","status":"1","imageUrl":"images/ca09570497f14175b29cc0be930be12c.png"}]
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
         * storeId : 1
         * name : 口袋牧场门店
         * address : 河南省郑州市二七区京莎B座
         * status : 1
         * imageUrl : images/ca09570497f14175b29cc0be930be12c.png
         */

        private int storeId;
        private String name;
        private String address;
        private String status;
        private String imageUrl;
        private int defautChoose;

        public int getDefautChoose() {
            return defautChoose;
        }

        public void setDefautChoose(int defautChoose) {
            this.defautChoose = defautChoose;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
