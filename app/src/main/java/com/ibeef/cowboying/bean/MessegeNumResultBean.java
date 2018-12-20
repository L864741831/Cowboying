package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/12/19 15:50
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MessegeNumResultBean {

    /**
     * code : 000000
     * message :
     * bizData : {"sysMsgCount":1,"eatMeatCount":1,"adoptCount":1,"shopCount":1,"couponCount":1}
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

    public  class BizDataBean {
        /**
         * sysMsgCount : 1
         * eatMeatCount : 1
         * adoptCount : 1
         * shopCount : 1
         * couponCount : 1
         */

        private int sysMsgCount;
        private int eatMeatCount;
        private int adoptCount;
        private int shopCount;
        private int couponCount;

        public int getSysMsgCount() {
            return sysMsgCount;
        }

        public void setSysMsgCount(int sysMsgCount) {
            this.sysMsgCount = sysMsgCount;
        }

        public int getEatMeatCount() {
            return eatMeatCount;
        }

        public void setEatMeatCount(int eatMeatCount) {
            this.eatMeatCount = eatMeatCount;
        }

        public int getAdoptCount() {
            return adoptCount;
        }

        public void setAdoptCount(int adoptCount) {
            this.adoptCount = adoptCount;
        }

        public int getShopCount() {
            return shopCount;
        }

        public void setShopCount(int shopCount) {
            this.shopCount = shopCount;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }
    }
}
