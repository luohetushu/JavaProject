package com.project.impel;

import com.project.service.IVoteService;

import java.util.Arrays;

public class VoteImpel implements IVoteService {

    VoteStudent[] students = new VoteStudent[]{new VoteStudent(1, "张三", 0),
            new VoteStudent(2, "李四", 0), new VoteStudent(3, "王五", 0),
            new VoteStudent(4, "赵六", 0)};

    @Override
    public boolean vote(long sid) {
        for (VoteStudent student : students) {
            if (student.getSid() == sid){
                student.setVotes(student.getVotes() + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public VoteStudent[] allData() {
        for (VoteStudent student: this.students){
            System.out.println(student);
        }
        return this.students;
    }

    @Override
    public VoteStudent[] result() {
        Arrays.sort(this.students);  //排序
        return this.students;
    }
}
