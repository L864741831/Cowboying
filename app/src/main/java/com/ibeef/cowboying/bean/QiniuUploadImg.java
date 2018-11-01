package com.ibeef.cowboying.bean;

/**
 * @Author $ls
 * @Date 2018/4/21 15:31
 * @Description
 */

public class QiniuUploadImg {


    /**
     * retCode : 1
     * retMsg : hLmj0BXrSEvJMGM0wERTxYhl0BAMXoH_5Ka7r_d6:q6Nos8aWo-mH_DoNu5ZxloNKe74=:eyJzY29wZSI6InliLWF2YXRhciIsImRlYWRsaW5lIjoxNTQxMDYyMjU5fQ==
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
