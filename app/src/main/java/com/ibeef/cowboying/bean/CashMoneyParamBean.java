package com.ibeef.cowboying.bean;

/**
 * @author ls
 * @date on 2018/11/12 15:41
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class CashMoneyParamBean {
    private String amount;
    private String type;
    private String account;
    private String realName;
    private String password;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
