package dev.melis.engelsizgonuller.services.profile;



import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProfileInformationServiceRequest {

    private String name;
    private String surname;
    private String email;
    private int userAge;
    private String userJob;
    private String aboutUser;
    private LocalDate birthday;

    public ProfileInformationServiceRequest setUsername(String name){
        this.name=name;
        return this;
    }
    public ProfileInformationServiceRequest setUserSurname(String surname){
        this.surname=surname;
        return this;
    }
    public ProfileInformationServiceRequest setUserEmail(String email){
        this.email=email;
        return this;
    }
    public int setUserAge(int userAge){
        this.userAge=userAge;
        return userAge;
    }
    public ProfileInformationServiceRequest setUserJob(String userJob){
        this.userJob=userJob;
        return this;
    }
    public ProfileInformationServiceRequest setAboutUser(String aboutUser){
        this.aboutUser=aboutUser;
        return this;
    }
    public ProfileInformationServiceRequest setBirthday(LocalDate birthday){
        this.birthday=birthday;
        return this;
    }
}
