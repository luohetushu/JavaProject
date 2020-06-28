package com.project.impel;

import java.util.Date;
import java.util.Objects;

public class HeiRiderImpel {
    String name;
    int age;
    String year;
    Date birthday;
    RiderImpel rider;  //级联

    public HeiRiderImpel() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public RiderImpel getRider() {
        return rider;
    }

    public void setRider(RiderImpel rider) {
        this.rider = rider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeiRiderImpel that = (HeiRiderImpel) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(year, that.year) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, year, birthday);
    }

    @Override
    public String toString() {
        return "HeiRiderImpel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", year='" + year + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
