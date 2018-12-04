package com.ibeef.cowboying.bean;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author ls
 * @date on 2018/12/3 10:53
 * @describe
 * @package com.ibeef.cowboying.bean
 **/
public class StoreCarResultBean implements Serializable {
    private int defautChoose;
    private boolean isChoose;
    private int num;

    public int getDefautChoose() {
        return defautChoose;
    }

    public void setDefautChoose(int defautChoose) {
        this.defautChoose = defautChoose;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
