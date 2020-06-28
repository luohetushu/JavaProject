package com.project.menu;

import com.project.custom.NetAnnotation;
import com.project.factory.Factory;
import com.project.impel.UserImpel;
import com.project.service.IUserService;

import javax.sql.DataSource;

@NetAnnotation(clazz = UserImpel.class)
public class LoginMenu {
    private IUserService iUser;

    public LoginMenu() {
        NetAnnotation anno = LoginMenu.class.getAnnotation(NetAnnotation.class);
        this.iUser = (IUserService) Factory.getUserInstance(anno.clazz());
    }

    public void login(){
        this.iUser.login(null, null);
    }


}
