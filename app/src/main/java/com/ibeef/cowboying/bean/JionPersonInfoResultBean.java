package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/11/14 10:55
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class JionPersonInfoResultBean {

    /**
     * code : 000000
     * message : null
     * bizData : [{"userId":48,"mobile":"18503876318","nickName":"PPY4Q370ZQ","headImage":"images/db8e41fbb42527811b4760be0698f957.jpg","vipLevel":"0","inviterId":1,"inviteCode":"13E0IU09","realName":"","realCardNo":"","isValidate":""}]
     */

    private String code;
    private String message;
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

    public List<BizDataBean> getBizData() {
        return bizData;
    }

    public void setBizData(List<BizDataBean> bizData) {
        this.bizData = bizData;
    }

    public  class BizDataBean {
        /**
         * userId : 48
         * mobile : 18503876318
         * nickName : PPY4Q370ZQ
         * headImage : images/db8e41fbb42527811b4760be0698f957.jpg
         * vipLevel : 0
         * inviterId : 1
         * inviteCode : 13E0IU09
         * realName :
         * realCardNo :
         * isValidate :
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

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public int getInviterId() {
            return inviterId;
        }

        public void setInviterId(int inviterId) {
            this.inviterId = inviterId;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
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

        public String getIsValidate() {
            return isValidate;
        }

        public void setIsValidate(String isValidate) {
            this.isValidate = isValidate;
        }
    }
}
