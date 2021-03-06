package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/12 14:05
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CowManInfosResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : {"myCattleCount":0,"myTotalAssets":1.0E7,"myCreditAmount":null,"myBeefAmount":0,"myCouponCount":4}
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
         * myCattleCount : 0
         * myTotalAssets : 1.0E7
         * myCreditAmount : null
         * myBeefAmount : 0.0
         * myCouponCount : 4
         */

        private int myCattleCount;
        private int msgCount;
        private double myTotalAssets;
        private double myCreditAmount;
        private double myBeefAmount;
        private int myCouponCount;
        private boolean haveVipCard;

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public boolean isHaveVipCard() {
            return haveVipCard;
        }

        public void setHaveVipCard(boolean haveVipCard) {
            this.haveVipCard = haveVipCard;
        }


        public int getMyCattleCount() {
            return myCattleCount;
        }

        public void setMyCattleCount(int myCattleCount) {
            this.myCattleCount = myCattleCount;
        }

        public double getMyTotalAssets() {
            return myTotalAssets;
        }

        public void setMyTotalAssets(double myTotalAssets) {
            this.myTotalAssets = myTotalAssets;
        }

        public double getMyCreditAmount() {
            return myCreditAmount;
        }

        public void setMyCreditAmount(double myCreditAmount) {
            this.myCreditAmount = myCreditAmount;
        }

        public double getMyBeefAmount() {
            return myBeefAmount;
        }

        public void setMyBeefAmount(double myBeefAmount) {
            this.myBeefAmount = myBeefAmount;
        }

        public int getMyCouponCount() {
            return myCouponCount;
        }

        public void setMyCouponCount(int myCouponCount) {
            this.myCouponCount = myCouponCount;
        }
    }
}
