package com.ibeef.cowboying.model;

import com.ibeef.cowboying.api.ApiService;
import com.ibeef.cowboying.base.LoginBase;
import com.ibeef.cowboying.config.Constant;

import rxfamily.net.HttpService;

/**
 * @author ls
 * @date on 2018/10/7 14:05
 * @describe 登录
 * @package com.ranhan.cowboying.model
 **/
public class LoginModel implements LoginBase.IModel {
    private HttpService httpService;
    private ApiService service;

    public LoginModel() {
        httpService = HttpService.getInstance(Constant.BASE_URL,false);
        service = httpService.getHttpService().create(ApiService.class);
    }

}
