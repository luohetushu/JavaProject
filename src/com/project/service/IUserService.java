package com.project.service;

public interface IUserService {
    /**
     * 登陆
     * @param name
     * @param password
     * @return
     */
    public boolean login(String name, String password);

    /**
     * 退出
     * @return
     */
    public boolean isExit();

}
