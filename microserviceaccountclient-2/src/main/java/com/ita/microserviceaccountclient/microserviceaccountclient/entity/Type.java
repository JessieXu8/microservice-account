package com.ita.microserviceaccountclient.microserviceaccountclient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ita.microserviceaccountclient.microserviceaccountclient.entity.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {

    public Type(){

    }
    private Integer id;
    private String type;
    private String imgUrl;
    private String accountKind;
    private List<Account> accountList = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name = "account_kind")
    public String getAccountKind() {
        return accountKind;
    }

    public void setAccountKind(String accountKind) {
        this.accountKind = accountKind;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch = FetchType.LAZY)
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
