package com.uploadcsv.demo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membersInfo")
public class Members {
    @Id
    @Column(name = "membername")
    private String membername;

    @Column(name = "memeberid")
    private String memberid;

    @Column(name = "regidate")
    private String regidate;

    @Column(name = "expdate")
    private String expdate;

    public Members(){
        
    }

    public Members(String membername, String memberid, String regidate, String expdate){
        this.membername = membername;
        this.memberid = memberid;
        this.regidate = regidate;
        this.expdate = expdate;
    }

    public String getName(){
        return membername;
    }

    public void setName(String membername){
        this.membername = membername;
    }

    public String getMemberid(){
        return memberid;
    }
    public void setMemberid(String memberid){
        this.memberid = memberid;
    }

    public String getRegidate(){
        return regidate;
    }

    public void setRgidate(String regidate){
        this.regidate = regidate;
    }

    public String getExpdate(){
        return expdate;
    }

    public void setExpdate(String expdate){
        this.expdate = expdate;
    }

    @Override
    public String toString(){
        return "Member [membername=" + membername + ", memberid=" + memberid + ", regidate=" + regidate + ", expdate=" + expdate + "]";
    }
}