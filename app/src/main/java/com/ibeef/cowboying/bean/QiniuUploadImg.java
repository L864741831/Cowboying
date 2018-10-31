package com.ibeef.cowboying.bean;

/**
 * @Author $ls
 * @Date 2018/4/21 15:31
 * @Description
 */

public class QiniuUploadImg {

    /**
     * retCode : 1
     * retMsg : nuQ0djrUDXHEsWFp5SLykFL3wLUgva6JyEckydoc:936QJla3s1OZUN8oHn1rg6MH69E=:eyJzY29wZSI6InliLWF2YXRhciIsImRlYWRsaW5lIjoxNTI0Mjk5NTM1fQ==
     * result : 获取token成功！
     */

    private int retCode;
    private String retMsg;
    private String result;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
