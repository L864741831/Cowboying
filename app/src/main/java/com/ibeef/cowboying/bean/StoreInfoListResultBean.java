package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/5 11:45
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class StoreInfoListResultBean {


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
         * cartProductNum : null
         * shopProductResVo : {"productId":24,"code":"201812050004","name":"牛头肉也不错啊","status":"1","categoryId":3,"specification":"269*3","price":600,"carriage":100,"weight":null,"marketPrice":null,"stock":1000,"saleNum":null,"describes":null}
         * productImages : [{"id":109,"productId":24,"imageUrl":"images/ca09570497f14175b29cc0be930be12c.png","type":"1"},{"id":110,"productId":24,"imageUrl":"images/ca09570497f14175b29cc0be930be12c.png","type":"2"}]
         * categoryResVo : {"categoryId":3,"name":"牛腿肉","imageUrl":"images/b6373eef1e9345c68d4c78f150a70662.jpg","status":"1"}
         * productVideoResVos : [{"id":62,"productId":24,"name":null,"videoCode":"bf0dbd6aa57942e18347ceddc67a904b","type":"1","videoUrl":"http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1543985882&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=4rsT1gmX5cnbpuJIBGqRTva7U1A%3D"}]
         */

        private int cartProductNum;
        private ShopProductResVoBean shopProductResVo;
        private CategoryResVoBean categoryResVo;
        private List<ProductImagesBean> productImages;
        private List<ProductVideoResVosBean> productVideoResVos;

        public int getCartProductNum() {
            return cartProductNum;
        }

        public void setCartProductNum(int cartProductNum) {
            this.cartProductNum = cartProductNum;
        }

        public ShopProductResVoBean getShopProductResVo() {
            return shopProductResVo;
        }

        public void setShopProductResVo(ShopProductResVoBean shopProductResVo) {
            this.shopProductResVo = shopProductResVo;
        }

        public CategoryResVoBean getCategoryResVo() {
            return categoryResVo;
        }

        public void setCategoryResVo(CategoryResVoBean categoryResVo) {
            this.categoryResVo = categoryResVo;
        }

        public List<ProductImagesBean> getProductImages() {
            return productImages;
        }

        public void setProductImages(List<ProductImagesBean> productImages) {
            this.productImages = productImages;
        }

        public List<ProductVideoResVosBean> getProductVideoResVos() {
            return productVideoResVos;
        }

        public void setProductVideoResVos(List<ProductVideoResVosBean> productVideoResVos) {
            this.productVideoResVos = productVideoResVos;
        }

        public  class ShopProductResVoBean {
            /**
             * productId : 24
             * code : 201812050004
             * name : 牛头肉也不错啊
             * status : 1
             * categoryId : 3
             * specification : 269*3
             * price : 600.0
             * carriage : 100.0
             * weight : null
             * marketPrice : null
             * stock : 1000
             * saleNum : null
             * describes : null
             */

            private int productId;
            private String code;
            private String name;
            private String status;
            private int categoryId;
            private String specification;
            private double price;
            private double carriage;
            private Object weight;
            private Object marketPrice;
            private int stock;
            private Object saleNum;
            private String describes;
            private int defautChoose;
            private boolean isChoose;
            private int num;

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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getCarriage() {
                return carriage;
            }

            public void setCarriage(double carriage) {
                this.carriage = carriage;
            }

            public Object getWeight() {
                return weight;
            }

            public void setWeight(Object weight) {
                this.weight = weight;
            }

            public Object getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(Object marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public Object getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(Object saleNum) {
                this.saleNum = saleNum;
            }

            public String getDescribes() {
                return describes;
            }

            public void setDescribes(String describes) {
                this.describes = describes;
            }
        }

        public  class CategoryResVoBean {
            /**
             * categoryId : 3
             * name : 牛腿肉
             * imageUrl : images/b6373eef1e9345c68d4c78f150a70662.jpg
             * status : 1
             */

            private int categoryId;
            private String name;
            private String imageUrl;
            private String status;

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }

        public  class ProductImagesBean {
            /**
             * id : 109
             * productId : 24
             * imageUrl : images/ca09570497f14175b29cc0be930be12c.png
             * type : 1
             */

            private int id;
            private int productId;
            private String imageUrl;
            private String type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public  class ProductVideoResVosBean {
            /**
             * id : 62
             * productId : 24
             * name : null
             * videoCode : bf0dbd6aa57942e18347ceddc67a904b
             * type : 1
             * videoUrl : http://outin-e8c66f46a80d11e8a46500163e1c60dc.oss-cn-shanghai.aliyuncs.com/bf0dbd6aa57942e18347ceddc67a904b/covers/7ce1abcf387d42c4aedaf54cd055c946-00001.jpg?Expires=1543985882&OSSAccessKeyId=LTAInFumgYEtNMvC&Signature=4rsT1gmX5cnbpuJIBGqRTva7U1A%3D
             */

            private int id;
            private int productId;
            private String name;
            private String videoCode;
            private String type;
            private String videoUrl;
            private String videoCoverUrl;

            public String getVideoCoverUrl() {
                return videoCoverUrl;
            }

            public void setVideoCoverUrl(String videoCoverUrl) {
                this.videoCoverUrl = videoCoverUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getVideoCode() {
                return videoCode;
            }

            public void setVideoCode(String videoCode) {
                this.videoCode = videoCode;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
