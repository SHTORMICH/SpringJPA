package com.epam.kabaldin.model;

import java.math.BigDecimal;

public interface UserAccount {

    Long getId();
    void setId(Long id);
    BigDecimal getPrepaidMoney();
    void setPrepaidMoney(BigDecimal prepaidMoney);
}
