package com.epam.kabaldin.model;

/**
 * Created by maksym_govorischev on 14/03/14.
 */
public interface User extends Entity{
    /**
     * User Id. UNIQUE.
     * @return User Id.
     */
    long getId();
    void setId(long id);
    String getName();
    void setName(String name);

    UserAccount getUserAccount();

    void setUserAccount(UserAccount userAccount);

    /**
     * User email. UNIQUE.
     * @return User email.
     */
    String getEmail();
    void setEmail(String email);
}
