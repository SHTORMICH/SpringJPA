package com.epam.kabaldin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Sport {
    @Id
    @Field
    private String id;
    @Field
    private String sportName;
    @Field
    private String sportProficiency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportProficiency() {
        return sportProficiency;
    }

    public void setSportProficiency(String sportProficiency) {
        this.sportProficiency = sportProficiency;
    }
}
