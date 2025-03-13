package com.avoda.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name="users")
public class UsersType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    
    private boolean isActive;

    private Date registrationDate;

    private UsersType userTypeId;
}
