package com.project.demo;

import com.project.factory.Factory;
import com.project.impel.VoteImpel;
import com.project.menu.LoginMenu;
import com.project.menu.Menu;
import com.project.proxy.ProxyHandle;
import com.project.service.IVoteService;
import com.project.utils.InputUtils;

public class TestDemo {
    public static void main(String[] args) {
        //用户登录
        //LoginMenu loginMenu = new LoginMenu();
        //loginMenu.login();

        //投票 菜单形式
        //new Menu();

        IVoteService service = new VoteImpel();
        ProxyHandle proxy = new ProxyHandle();
        IVoteService voteService = (IVoteService) proxy.bind(service);
        voteService.vote(3);
        voteService.result();
        voteService.allData();


    }
}
