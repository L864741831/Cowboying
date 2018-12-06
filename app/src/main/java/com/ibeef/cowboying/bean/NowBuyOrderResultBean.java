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
     * bizData : {"address":{"id":null,"name":null,"mobile":null,"provinceId":null,"cityId":null,"regionId":null,"detailAddress":null,"isDefualt":null},"products":[{"productId":12,"name":"牛头肉","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":600,"stock":1000,"quantity":3,"productAmount":1800},{"productId":19,"name":"来两块的吗","productMainImage":"images/1cf94a995c9d32b694afb7b245e2f997","specification":"333","weight":null,"price":1,"stock":10000,"quantity":5,"productAmount":5}],"carrageAmount":0,"orderAmount":18.05,"totalQuantity":8}
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
         * address : {"id":null,"name":null,"mobile":null,"provinceId":null,"cityId":null,"regionId":null,"detailAddress":null,"isDefualt":null}
         * products : [{"productId":12,"name":"牛头肉","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":600,"stock":1000,"quantity":3,"productAmount":1800},{"productId":19,"name":"来两块的吗","productMainImage":"images/1cf94a995c9d32b694afb7b245e2f997","specification":"333","weight":null,"price":1,"stock":10000,"quantity":5,"productAmount":5}]
         * carrageAmount : 0
         * orderAmount : 18.05
         * totalQuantity : 8
         */

        private AddressBean address;
        private int carrageAmount;
        private double orderAmount;
        private int totalQuantity;
        private List<ProductsBean> products;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
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

        public  class AddressBean implements Serializable{
            /**
             * id : null
             * name : null
             * mobile : null
             * provinceId : null
             * cityId : null
             * regionId : null
             * detailAddress : null
             * isDefualt : null
             */

            private int id;
            private String name;
            private String mobile;
            private String province;
            private String city;
            private String region;
            private String detailAddress;
            private int isDefualt;

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

            public int getIsDefualt() {
                return isDefualt;
            }

            public void setIsDefualt(int isDefualt) {
                this.isDefualt = isDefualt;
            }
        }

        public  class ProductsBean implements Serializable{
            /**
             * productId : 12
             * name : 牛头肉
             * productMainImage : images/ca09570497f14175b29cc0be930be12c.png
             * specification : 269*3
             * weight : null
             * price : 600.0
             * stock : 1000
             * quantity : 3
             * productAmount : 1800.0
             */

            private int productId;
            private String name;
            private String productMainImage;
            private String specification;
            private Object weight;
            private double price;
            private int stock;
            private int quantity;
            private double productAmount;
            private int defautChoose;

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

            public String getProductMainImage() {
                return productMainImage;
            }

            public void setProductMainImage(String productMainImage) {
                this.productMainImage = productMainImage;
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

            public double getProductAmount() {
                return productAmount;
            }

            public void setProductAmount(double productAmount) {
                this.productAmount = productAmount;
            }
        }
    }
}
