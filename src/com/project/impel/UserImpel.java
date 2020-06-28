package com.project.impel;

import com.project.service.IUserService;

public class UserImpel implements IUserService {

    private int count;  //登录次数

    public UserImpel() {
    }

    @Override
    public boolean login(String name, String password) {
        this.count++;
        return "root".equals(name) && "admin".equals(password);
    }

    @Override
    public boolean isExit() {
        return (this.count >= 3);  //登陆超出三次默认退出
    }

}
