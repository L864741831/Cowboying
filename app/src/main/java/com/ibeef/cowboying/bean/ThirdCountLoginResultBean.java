package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/10/29 15:43
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ThirdCountLoginResultBean {


    /**
     * code : 000000
     * message : null
     * bizData : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NDE4MTg1MjEsImxvZ2luVHlwZSI6IjMiLCJpc3MiOiJ3d3cueWFiZWkuc2hvcCIsImV4cCI6MTU0NDQxMDUyMSwiaWF0IjoxNTQxODE4NTIxLCJ1c2VySWQiOjQyfQ.Yzc_uaP9DLd9LF9RitmPWQqstD4f_10-cisDnxtXNu0","mobile":null}
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
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1NDE4MTg1MjEsImxvZ2luVHlwZSI6IjMiLCJpc3MiOiJ3d3cueWFiZWkuc2hvcCIsImV4cCI6MTU0NDQxMDUyMSwiaWF0IjoxNTQxODE4NTIxLCJ1c2VySWQiOjQyfQ.Yzc_uaP9DLd9LF9RitmPWQqstD4f_10-cisDnxtXNu0
         * mobile : null
         */

        private String token;
        private String mobile;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
