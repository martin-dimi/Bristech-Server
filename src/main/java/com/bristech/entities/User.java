package com.bristech.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
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
    private String name;
    @Column(name = "backdrop")
    private String backdrop;


    public User() {
        //for Hibernate
    }

    public User(String email, String name, String backdrop) {
        this.email = email;
        this.name = name;
        this.backdrop = backdrop;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
