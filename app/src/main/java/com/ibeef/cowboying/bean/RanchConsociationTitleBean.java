package com.ibeef.cowboying.bean;

import java.util.List;



/**
 * @author Administrator
 */
public class RanchConsociationTitleBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public  class DataBean {

        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
