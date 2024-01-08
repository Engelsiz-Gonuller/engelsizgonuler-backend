package dev.melis.engelsizgonuller.services.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid",nullable = false)
    long id;

    @Column(name = "name",nullable = false)
    private  String userName;

    @Column(name = "surname",nullable = false)
    private String userSurname;

    @Column(name = "age")
    private LocalDate birthday;

    @Column(name = "age")
    private int userAge;

    @Column(name = "job")
    private String userJob;

    @Lob
    @Column(name = "about",columnDefinition = "text")
    private String aboutUser;

    @Column(name = "email",nullable = false)
    @Setter
    private String userEmail;

    @Column(name = "password",nullable = false)
    private String userPassword;

    @Column(name = "usertype",nullable = false)
    private UserType userType;

    @Column(name = "dateofregistration",nullable = false)
    private LocalDate dateOfRegistration;

    @Column(name = "role")
    private UserRole role;

    private boolean isLocked;
    private boolean isEnabled;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;

    public User(){
     this.id =0L;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
