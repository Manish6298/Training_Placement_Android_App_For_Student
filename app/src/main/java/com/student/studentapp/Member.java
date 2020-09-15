package com.student.studentapp;

public class Member {

    // Member

    String name;
    String email;

    String eno;
    String mno;



    String address;
    String  date;
    String Gender;

    // Skills

    String hobbies;
    String internship;
    String achieve;

    // Qualification

    String ssc,hsc,diploma,fe,se,te,be;
    String live,dead;
    String syear,sbranch;


    // Default Constuctor

    public Member() {
    }


    //  Paramertrized Constructor

    public Member(String name, String email, String eno, String mno, String address, String date, String gender, String hobbies, String internship, String achieve, String ssc, String hsc, String diploma, String fe, String se, String te, String be, String live, String dead, String syear, String sbranch) {
        this.name = name;
        this.email = email;
        this.eno = eno;
        this.mno = mno;
        this.address = address;
        this.date = date;
        Gender = gender;
        this.hobbies = hobbies;
        this.internship = internship;
        this.achieve = achieve;
        this.ssc = ssc;
        this.hsc = hsc;
        this.diploma = diploma;
        this.fe = fe;
        this.se = se;
        this.te = te;
        this.be = be;
        this.live = live;
        this.dead = dead;
        this.syear = syear;
        this.sbranch = sbranch;
    }


    // Member Method

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


    // Skills Method

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getInternship() {
        return internship;
    }

    public void setInternship(String internship) {
        this.internship = internship;
    }

    public String getAchieve() {
        return achieve;
    }

    public void setAchieve(String achieve) {
        this.achieve = achieve;
    }

    // Qualification Method


    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getHsc() {
        return hsc;
    }

    public void setHsc(String hsc) {
        this.hsc = hsc;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getFe() {
        return fe;
    }

    public void setFe(String fe) {
        this.fe = fe;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getBe() {
        return be;
    }

    public void setBe(String be) {
        this.be = be;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    public String getSyear() {
        return syear;
    }

    public void setSyear(String syear) {
        this.syear = syear;
    }

    public String getSbranch() {
        return sbranch;
    }

    public void setSbranch(String sbranch) {
        this.sbranch = sbranch;
    }
}
