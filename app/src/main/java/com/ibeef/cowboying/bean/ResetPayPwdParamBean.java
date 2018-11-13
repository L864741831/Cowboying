package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/13 15:44
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class ResetPayPwdParamBean {
    private String newPayPassWord;
    private String oldPayPassWord;

    public String getNewPayPassWord() {
        return newPayPassWord;
    }

    public void setNewPayPassWord(String newPayPassWord) {
        this.newPayPassWord = newPayPassWord;
    }

    public String getOldPayPassWord() {
        return oldPayPassWord;
    }

    public void setOldPayPassWord(String oldPayPassWord) {
        this.oldPayPassWord = oldPayPassWord;
    }
}
