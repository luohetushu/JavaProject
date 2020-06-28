package com.project.proxy;

import com.project.service.IUserService;
import com.project.utils.InputUtils;

/**
 * 代理设计：静态代理
 * 代理设计模式：真实业务只实现核心功能，代理设计辅助逻辑处理
 */
public class UserProxy implements IUserService {

    private IUserService iUser;  //代理对象，业务接口实例

    public UserProxy(Object iUser) {
        this.iUser = (IUserService) iUser;
    }

    @Override
    public boolean login(String name, String password) {
        while (!this.isExit()){
            String userName = InputUtils.getInputString("请输入登陆信息（用户名/密码）：");
            if (userName.matches("\\w+/\\w+")){
                String[] userInfo = userName.split("/");
                if (this.iUser.login(userInfo[0], userInfo[1])){
                    System.out.println("输入正确，登陆成功");
                    return true;
                } else {
                    System.out.println("用户名密码有误，登陆失败，请重新输入");
                }
            } else {
                String userPwd = InputUtils.getInputString("请输入登陆密码：");
                if (this.iUser.login(userName, userPwd)){
                    System.out.println("输入正确，登陆成功");
                    return true;
                } else {
                    System.out.println("用户名密码有误，登陆失败，请重新输入");
                }
            }
        }
        System.out.println("您已多次输入用户名密码有误，登陆失败");
        return false;
    }

    @Override
    public boolean isExit() {
        return this.iUser.isExit();
    }
}
