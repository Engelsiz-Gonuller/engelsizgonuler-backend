package dev.melis.engelsizgonuller.services.profile;



import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateUserProfileServiceRequest {

    private String name;
    private String surname;
    private String userJob;
    private String aboutUser;
    private LocalDate birthday;

    public UpdateUserProfileServiceRequest setUsername(String name){
        this.name=name;
        return this;
    }
    public UpdateUserProfileServiceRequest setUserSurname(String surname){
        this.surname=surname;
        return this;
    }
    public UpdateUserProfileServiceRequest setUserJob(String userJob){
        this.userJob=userJob;
        return this;
    }
    public UpdateUserProfileServiceRequest setAboutUser(String aboutUser){
        this.aboutUser=aboutUser;
        return this;
    }
    public UpdateUserProfileServiceRequest setBirthday(LocalDate birthday){
        this.birthday=birthday;
        return this;
    }
}
