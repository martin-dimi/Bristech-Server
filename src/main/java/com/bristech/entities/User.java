package com.bristech.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuppressWarnings("WeakerAccess")
@Entity
@Table(name = "users")
public class User {

    //This is the user class

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String email;
    @Column(name = "name")
    private String firstName;
    @Column(name = "backdrop")
    private String picture;


    public User() {
        //for Hibernate
    }

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
