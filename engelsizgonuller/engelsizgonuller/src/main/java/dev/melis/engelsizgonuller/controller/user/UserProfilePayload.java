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
    String birthDate;
    LocalDate registerDate;
    UserType userType;

    public UserProfilePayload(User user){
        name=user.getName();
        surname=user.getUserSurname();
        job= user.getUserJob();
        about= user.getAboutUser();
        int age=LocalDate.now().getYear()- user.getBirthday().getYear();
        birthDate=user.getBirthday().toString()+ " ( "+ age+" ) ";
        registerDate=user.getDateOfRegistration();
        userType=user.getUserType();
    }
}
