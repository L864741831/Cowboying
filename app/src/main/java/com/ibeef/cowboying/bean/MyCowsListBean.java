package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @Author $ls
 * @Date 2018/3/31 11:30
 * @Description
 */

public class MyCowsListBean {

    /**
     * status : 000000
     * message :
     * data : [{"deliveryOrderCode":"201803260051","payAmount":12.5,"payOrderCode":"201803260050","shopId":"21","shopName":"丫贝自营","status":"1","totalQuantity":1,"products":[{"price":12.5,"image":"images/79e3e8349ed045aea9fc67bd392001be.jpg","name":"韩版清新天然贝壳打磨小雏菊花瓣甜美花朵镀银针耳钉耳饰女配饰品","productId":"335","quantity":1,"spec":"尺寸：约1cm 颜色：银色","stock":null,"itemId":"447"}]},{"deliveryOrderCode":"201803260041","payAmount":12.5,"payOrderCode":"201803260040","shopId":"21","shopName":"丫贝自营","status":"1","totalQuantity":1,"products":[{"price":12.5,"image":"images/79e3e8349ed045aea9fc67bd392001be.jpg","name":"韩版清新天然贝壳打磨小雏菊花瓣甜美花朵镀银针耳钉耳饰女配饰品","productId":"335","quantity":1,"spec":"尺寸：约1cm 颜色：银色","stock":null,"itemId":"447"}]},{"deliveryOrderCode":"201803200041","payAmount":12.5,"payOrderCode":"201803200040","shopId":"21","shopName":"丫贝自营","status":"1","totalQuantity":1,"products":[{"price":12.5,"image":"images/79e3e8349ed045aea9fc67bd392001be.jpg","name":"韩版清新天然贝壳打磨小雏菊花瓣甜美花朵镀银针耳钉耳饰女配饰品","productId":"335","quantity":1,"spec":"尺寸：约1cm 颜色：银色","stock":null,"itemId":"447"}]}]
     */

    private String status;
    private String message;
    private int currentPage;
    private int pageSize;
    private List<DataBean> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public  class DataBean {
        /**
         * deliveryOrderCode : 201803260051
         * payAmount : 12.5
         * payOrderCode : 201803260050
         * shopId : 21
         * shopName : 丫贝自营
         * status : 1
         * totalQuantity : 1
         * products : [{"price":12.5,"image":"images/79e3e8349ed045aea9fc67bd392001be.jpg","name":"韩版清新天然贝壳打磨小雏菊花瓣甜美花朵镀银针耳钉耳饰女配饰品","productId":"335","quantity":1,"spec":"尺寸：约1cm 颜色：银色","stock":null,"itemId":"447"}]
         */

        private String deliveryOrderCode;
        private double payAmount;
        private String payOrderCode;
        private String shopId;
        private String shopName;
        private String status;
        private int totalQuantity;
        private List<ProductsBean> products;
        private Integer groupId;
        private String isGroupEnd;

        public String getIsGroupEnd() {
            return isGroupEnd;
        }

        public void setIsGroupEnd(String isGroupEnd) {
            this.isGroupEnd = isGroupEnd;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public String getDeliveryOrderCode() {
            return deliveryOrderCode;
        }

        public void setDeliveryOrderCode(String deliveryOrderCode) {
            this.deliveryOrderCode = deliveryOrderCode;
        }

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public String getPayOrderCode() {
            return payOrderCode;
        }

        public void setPayOrderCode(String payOrderCode) {
            this.payOrderCode = payOrderCode;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public  class ProductsBean {
            /**
             * price : 12.5
             * image : images/79e3e8349ed045aea9fc67bd392001be.jpg
             * name : 韩版清新天然贝壳打磨小雏菊花瓣甜美花朵镀银针耳钉耳饰女配饰品
             * productId : 335
             * quantity : 1
             * spec : 尺寸：约1cm 颜色：银色
             * stock : null
             * itemId : 447
             */

            private double price;
            private String image;
            private String name;
            private String productId;
            private int quantity;
            private String spec;
            private Object stock;
            private String itemId;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public Object getStock() {
                return stock;
            }

            public void setStock(Object stock) {
                this.stock = stock;
            }

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }
        }
    }
}
