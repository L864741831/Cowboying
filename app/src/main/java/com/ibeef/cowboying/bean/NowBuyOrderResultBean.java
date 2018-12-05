package com.ibeef.cowboying.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 16:05
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class NowBuyOrderResultBean implements Serializable {

    /**
     * code : 000000
     * message :
     * bizData : {"address":null,"products":[{"productId":19,"name":"来两块的吗","specification":"333","weight":null,"price":1,"stock":10000,"quantity":5}],"carrageAmount":0,"orderAmount":5,"totalQuantity":5}
     */

    private String code;
    private String message;
    private BizDataBean bizData;

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

    public BizDataBean getBizData() {
        return bizData;
    }

    public void setBizData(BizDataBean bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean implements Serializable{
        /**
         * address : null
         * products : [{"productId":19,"name":"来两块的吗","specification":"333","weight":null,"price":1,"stock":10000,"quantity":5}]
         * carrageAmount : 0
         * orderAmount : 5.0
         * totalQuantity : 5
         */

        private String address;
        private int carrageAmount;
        private double orderAmount;
        private int totalQuantity;
        private List<ProductsBean> products;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCarrageAmount() {
            return carrageAmount;
        }

        public void setCarrageAmount(int carrageAmount) {
            this.carrageAmount = carrageAmount;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(int totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public  class ProductsBean implements Serializable{
            /**
             * productId : 19
             * name : 来两块的吗
             * specification : 333
             * weight : null
             * price : 1.0
             * stock : 10000
             * quantity : 5
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
}
