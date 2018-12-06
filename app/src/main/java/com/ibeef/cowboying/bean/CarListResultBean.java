package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 16:17
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CarListResultBean {

    /**
     * code : 000000
     * message :
     * bizData : [{"productId":2,"name":"牛头肉","specification":"269*24","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":3,"name":"牛头肉","specification":"269*2","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":9,"name":"牛胸肉7不7","specification":"269*1","weight":null,"price":600,"stock":2000,"quantity":null},{"productId":10,"name":"牛眼肉","specification":"269*87","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":12,"name":"牛头肉","specification":"269*3","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":13,"name":"大腿呀大腿","specification":"269*4","weight":null,"price":500,"stock":2000,"quantity":null},{"productId":20,"name":"牛牛酱可耐你78","specification":"500kg","weight":null,"price":1000,"stock":2500,"quantity":null},{"productId":23,"name":"牛头肉","specification":"269*6","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":24,"name":"牛头肉也不错啊","specification":"269*3","weight":null,"price":600,"stock":1000,"quantity":null},{"productId":25,"name":"牛头肉也不错啊啊","specification":"269*3","weight":null,"price":600,"stock":1000,"quantity":null}]
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
         * productId : 2
         * name : 牛头肉
         * specification : 269*24
         * weight : null
         * price : 600.0
         * stock : 1000
         * quantity : null
         */

        private int productId;
        private String name;
        private String specification;
        private String productMainImage;
        private Object weight;
        private double price;
        private int stock;
        private int quantity;
        private int defautChoose;
        private boolean isChoose;

        public String getProductMainImage() {
            return productMainImage;
        }

        public void setProductMainImage(String productMainImage) {
            this.productMainImage = productMainImage;
        }

        public int getDefautChoose() {
            return defautChoose;
        }

        public void setDefautChoose(int defautChoose) {
            this.defautChoose = defautChoose;
        }

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
