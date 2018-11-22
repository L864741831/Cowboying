package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/17 16:00
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class MyContractURLBean {

    /**
     * code : 000000
     * message :
     * bizData : http://pasture-contract.oss-cn-beijing.aliyuncs.com/contract/adoptContract.pdf?Expires=1542855426&OSSAccessKeyId=LTAIMwEKWL7OahCb&Signature=fQo4JBN2ZhbGFzEtERAG1ZZbnK8%3D
     */

    private String code;
    private String message;
    private String bizData;

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

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }
}
