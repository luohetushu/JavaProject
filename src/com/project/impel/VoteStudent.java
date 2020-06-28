package com.project.impel;

import java.util.Objects;

public class VoteStudent implements Comparable<VoteStudent> {

    long sid;
    String name;
    int votes;

    public VoteStudent(){
        this(5, "钱七", 0);
        System.out.println("****实例化票选对象****");
    }

    public VoteStudent(long sid, String name, int votes) {
        this.sid = sid;
        this.name = name;
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        VoteStudent student = (VoteStudent) o;
        return sid == student.sid &&
                votes == student.votes &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, votes);
    }

    @Override
    public int compareTo(VoteStudent voteStudent) {
        return voteStudent.votes - this.votes;  //从高到低
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString(){
        return this.sid + ": " + this.name + "[" + this.votes + "]";
    }

}
