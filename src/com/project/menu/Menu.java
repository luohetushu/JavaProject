package com.project.menu;

import com.project.factory.Factory;
import com.project.impel.VoteStudent;
import com.project.service.IVoteService;
import com.project.utils.InputUtils;

public class Menu {

    IVoteService voteService;

    public Menu() {
        this.voteService = Factory.getVoteInstance("com.project.impel.VoteImpel");
        //投票处理
        voteResult();
    }

    //投票处理
    private void voteResult(){
        VoteStudent[] data = this.voteService.allData();
        for (VoteStudent student: data) {
            System.out.println(student.toString());
        }
        int sid = 10;
        while (sid != 0){
            sid = InputUtils.getInputInt("请输入候选人序号（数字0结束）：");
            if (sid != 0){
                if (!this.voteService.vote(sid)){
                    System.out.println("此选票无效，请输入正确的候选人序号");
                }
            }
        }
        System.out.println("投票最终结果：");
        data = this.voteService.result();
        boolean vote = false;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i].getVotes() != data[i + 1].getVotes()){
                vote = true;
            }
        }
        if (vote){
            System.out.println("序号为" + data[0].getSid() + "号的"
                    + data[0].getName() + "以" + data[0].getVotes() + "张票数获选代表");
        } else {
            System.out.println("所有代表票数一致，增加一轮投票");
            voteResult();
        }

    }

}
