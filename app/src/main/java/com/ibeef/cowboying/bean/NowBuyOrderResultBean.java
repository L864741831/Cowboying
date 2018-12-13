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
     * message : “”
     * bizData : {"address":{"id":9,"name":"李","mobile":"18703643373","province":"浙江省","provinceId":330000,"city":"杭州市","cityId":330100,"region":"滨江区","regionId":330108,"detailAddress":"河南财经政法大学","isDefault":"1"},"products":[{"productId":3,"name":"牛头肉","productMainImage":null,"specification":"269*2","weight":null,"price":6,"stock":998,"quantity":3,"productAmount":18,"status":"1","carriage":0.01,"carriageAmount":0.03},{"productId":12,"name":"牛头肉","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":6,"stock":1000,"quantity":3,"productAmount":18,"status":"1","carriage":0.01,"carriageAmount":0.03},{"productId":25,"name":"牛头肉也不错啊啊","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":6,"stock":999,"quantity":2,"productAmount":12,"status":"1","carriage":1,"carriageAmount":2},{"productId":35,"name":"啦啦啦啦啦啦啦啦啦啦啦","productMainImage":"images/e71ff25f949b9d1c9d83f99e0ed99329","specification":"500kg","weight":null,"price":0.5,"stock":489,"quantity":1,"productAmount":0.5,"status":"1","carriage":2000,"carriageAmount":2000},{"productId":44,"name":"ddd","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"111*20g","weight":null,"price":10,"stock":979,"quantity":1,"productAmount":10,"status":"1","carriage":12,"carriageAmount":12},{"productId":46,"name":"这是牛啊","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"300g","weight":null,"price":300,"stock":9999,"quantity":2,"productAmount":600,"status":"1","carriage":1,"carriageAmount":2},{"productId":49,"name":"你确定这是牛排","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"300g","weight":null,"price":199,"stock":94,"quantity":2,"productAmount":398,"status":"1","carriage":1,"carriageAmount":2}],"carriageAmount":2018.06,"orderAmount":1056.5,"totalQuantity":14}
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
         * address : {"id":9,"name":"李","mobile":"18703643373","province":"浙江省","provinceId":330000,"city":"杭州市","cityId":330100,"region":"滨江区","regionId":330108,"detailAddress":"河南财经政法大学","isDefault":"1"}
         * products : [{"productId":3,"name":"牛头肉","productMainImage":null,"specification":"269*2","weight":null,"price":6,"stock":998,"quantity":3,"productAmount":18,"status":"1","carriage":0.01,"carriageAmount":0.03},{"productId":12,"name":"牛头肉","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":6,"stock":1000,"quantity":3,"productAmount":18,"status":"1","carriage":0.01,"carriageAmount":0.03},{"productId":25,"name":"牛头肉也不错啊啊","productMainImage":"images/ca09570497f14175b29cc0be930be12c.png","specification":"269*3","weight":null,"price":6,"stock":999,"quantity":2,"productAmount":12,"status":"1","carriage":1,"carriageAmount":2},{"productId":35,"name":"啦啦啦啦啦啦啦啦啦啦啦","productMainImage":"images/e71ff25f949b9d1c9d83f99e0ed99329","specification":"500kg","weight":null,"price":0.5,"stock":489,"quantity":1,"productAmount":0.5,"status":"1","carriage":2000,"carriageAmount":2000},{"productId":44,"name":"ddd","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"111*20g","weight":null,"price":10,"stock":979,"quantity":1,"productAmount":10,"status":"1","carriage":12,"carriageAmount":12},{"productId":46,"name":"这是牛啊","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"300g","weight":null,"price":300,"stock":9999,"quantity":2,"productAmount":600,"status":"1","carriage":1,"carriageAmount":2},{"productId":49,"name":"你确定这是牛排","productMainImage":"images/051cb18bfc2cac1b5d25229cb41423e6","specification":"300g","weight":null,"price":199,"stock":94,"quantity":2,"productAmount":398,"status":"1","carriage":1,"carriageAmount":2}]
         * carriageAmount : 2018.06
         * orderAmount : 1056.5
         * totalQuantity : 14
         */

        private AddressBean address;
        private double totalCarriageAmount;
        private double orderAmount;
        private double totalProductAmount;
        private int totalQuantity;
        private List<ProductsBean> products;

        public double getTotalProductAmount() {
            return totalProductAmount;
        }

        public void setTotalProductAmount(double totalProductAmount) {
            this.totalProductAmount = totalProductAmount;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }


        public double getTotalCarriageAmount() {
            return totalCarriageAmount;
        }

        public void setTotalCarriageAmount(double totalCarriageAmount) {
            this.totalCarriageAmount = totalCarriageAmount;
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
             * id : 9
             * name : 李
             * mobile : 18703643373
             * province : 浙江省
             * provinceId : 330000
             * city : 杭州市
             * cityId : 330100
             * region : 滨江区
             * regionId : 330108
             * detailAddress : 河南财经政法大学
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

        public  class ProductsBean implements Serializable{
            /**
             * productId : 3
             * name : 牛头肉
             * productMainImage : null
             * specification : 269*2
             * weight : null
             * price : 6.0
             * stock : 998
             * quantity : 3
             * productAmount : 18.0
             * status : 1
             * carriage : 0.01
             * carriageAmount : 0.03
             */

            private int productId;
            private String name;
            private String productMainImage;
            private String specification;
            private Object weight;
            private double price;
            private int stock;
            private int defautChoose;
            private int quantity;
            private double productAmount;
            private String status;
            private double carriage;
            private double carriageAmount;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getCarriage() {
                return carriage;
            }

            public void setCarriage(double carriage) {
                this.carriage = carriage;
            }

            public double getCarriageAmount() {
                return carriageAmount;
            }

            public void setCarriageAmount(double carriageAmount) {
                this.carriageAmount = carriageAmount;
            }
        }
    }
}
