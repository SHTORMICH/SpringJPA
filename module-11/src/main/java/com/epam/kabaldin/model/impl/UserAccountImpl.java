package com.epam.kabaldin.model.impl;

import com.epam.kabaldin.model.UserAccount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccountImpl implements UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prepaid_money")
    private Long prepaidMoney;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getPrepaidMoney() {
        return prepaidMoney;
    }

    @Override
    public void setPrepaidMoney(Long prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }
}
