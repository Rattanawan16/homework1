package com.practice.homework.models;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String firstname;
    private String lastname;

    public User() {
        super();
    }

    public User(String id, String firstname, String lastname) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
