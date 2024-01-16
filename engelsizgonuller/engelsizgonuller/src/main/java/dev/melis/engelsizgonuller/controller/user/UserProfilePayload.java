package dev.melis.engelsizgonuller.controller.user;

import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.services.model.user.UserType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserProfilePayload {

    String name;
    String surname;
    String job;
    String about;
    String email;
    String birthDate;
    LocalDate registerDate;
    UserType userType;

    public UserProfilePayload(User user){
        name=user.getName();
        surname=user.getUserSurname();
        job= user.getUserJob();
        email = user.getUsername();
        about= user.getAboutUser();
        int age=LocalDate.now().getYear()- (user.getBirthday() != null ? user.getBirthday().getYear() : 0);
        birthDate=(user.getBirthday() != null ? user.getBirthday().toString() : "0") +  " ( " +  age+" ) ";
        registerDate=user.getDateOfRegistration();
        userType=user.getUserType();
    }
}