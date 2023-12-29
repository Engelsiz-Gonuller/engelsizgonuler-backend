package dev.melis.engelsizgonuller.core.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid",nullable = false)
    @Setter
    long id;

    @Column(name = "name",nullable = false)
    @Setter
    private  String userName;

    @Column(name = "surname",nullable = false)
    @Setter
    private String userSurname;

    @Column(name = "age")
    @Setter
    private int userAge;

    @Column(name = "job")
    @Setter
    private String userJob;

    @Lob
    @Column(name = "about",columnDefinition = "text")
    @Setter
    private String aboutUser;

    @Column(name = "email",nullable = false)
    @Setter
    private String userEmail;

    @Column(name = "password",nullable = false)
    @Setter
    private String userPassword;

    @Column(name = "usertype",nullable = false)
    @Setter
    private UserType userType;
  public User(){
     this.id =0L;
 }

}
