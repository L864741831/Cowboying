package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 9:59
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class UserInfoResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : {"userId":13,"mobile":"18703643373","nickName":null,"headImage":null,"vipLevel":"0","inviterId":null,"inviteCode":"FJXWHFZ1","realName":null,"realCardNo":null,"isValidate":"0"}
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
         * userId : 13
         * mobile : 18703643373
         * nickName : null
         * headImage : null
         * vipLevel : 0
         * inviterId : null
         * inviteCode : FJXWHFZ1
         * realName : null
         * realCardNo : null
         * isValidate : 0
         */

        private int userId;
        private String mobile;
        private String nickName;
        private String headImage;
        private String vipLevel;
        private int inviterId;
        private String inviteCode;
        private String realName;
        private String realCardNo;
        private String isValidate;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getInviterId() {
            return inviterId;
        }

        public void setInviterId(int inviterId) {
            this.inviterId = inviterId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealCardNo() {
            return realCardNo;
        }

        public void setRealCardNo(String realCardNo) {
            this.realCardNo = realCardNo;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }


        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getIsValidate() {
            return isValidate;
        }

        public void setIsValidate(String isValidate) {
            this.isValidate = isValidate;
        }
    }
}
