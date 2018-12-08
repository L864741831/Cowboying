package com.ibeef.cowboying.bean;

import java.util.List;

/**
 * @author ls
 * @date on 2018/12/8 9:49
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ShowDeleveryResultBean {


    /**
     * code : 000000
     * message :
     * bizData : {"expressName":"顺丰速运","expressCode":"shunfeng","expressResVos":[{"context":"[上海市]快件已发车","status":"在途","time":"2018-12-03 15:59:41"},{"context":"[上海市]快件在【上海宝山杨行营业点】已装车,准备发往下一站","status":"在途","time":"2018-12-03 15:21:49"},{"context":"[上海市]顺丰速运 已收取快件","status":"揽收","time":"2018-12-03 13:51:59"}]}
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
         * expressName : 顺丰速运
         * expressCode : shunfeng
         * expressResVos : [{"context":"[上海市]快件已发车","status":"在途","time":"2018-12-03 15:59:41"},{"context":"[上海市]快件在【上海宝山杨行营业点】已装车,准备发往下一站","status":"在途","time":"2018-12-03 15:21:49"},{"context":"[上海市]顺丰速运 已收取快件","status":"揽收","time":"2018-12-03 13:51:59"}]
         */

        private String expressName;
        private String expressCode;
        private List<ExpressResVosBean> expressResVos;

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getExpressCode() {
            return expressCode;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public List<ExpressResVosBean> getExpressResVos() {
            return expressResVos;
        }

        public void setExpressResVos(List<ExpressResVosBean> expressResVos) {
            this.expressResVos = expressResVos;
        }

        public  class ExpressResVosBean {
            /**
             * context : [上海市]快件已发车
             * status : 在途
             * time : 2018-12-03 15:59:41
             */

            private String context;
            private String status;
            private String time;

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
