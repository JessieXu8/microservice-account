package com.ita.microservicetypeclient.microservicetypeclient.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.text.*;
import java.util.*;

@Entity
@Table(name = "account")
public class Account {

    public Account(){

    }

    private Integer id;
    private User user;
    private Type type;
    private Double amount;
    private String accountKind;
    private String remark;
    private String isDelete;

    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-dd HH:mm:ss")
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "account_kind")
    public String getAccountKind() {
        return accountKind;
    }


    public void setAccountKind(String accountKind) {
        this.accountKind = accountKind;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public boolean isIncome(){
        return type.getAccountKind().equals("income");
    }

    @Transient
    private String dateStr;

    public String getDateStr(){
        if(StringUtils.isEmpty(dateStr) && date != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = simpleDateFormat.format(date);
        }
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}

