package com.project.service;

import com.project.impel.VoteStudent;

public interface IVoteService {
    public boolean vote(long sid);  //根据编号投票
    public VoteStudent[] allData(); //所有的数据
    public VoteStudent[] result();  //投票结果
}
