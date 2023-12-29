package dev.melis.engelsizgonuller.core.registration;

import dev.melis.engelsizgonuller.core.dto.UserType;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegistrationServiceRequest {
    @Email
    private String email;

    @Length(min = 8)
    private String password;

    private String name;
    private String surname;
    private UserType userType;

    public RegistrationServiceRequest setEmail(String email){
        this.email=email;
        return this;
    }
    public RegistrationServiceRequest setPassword(String password){
        this.password=password;
        return this;
    }
    public RegistrationServiceRequest setName(String name){
        this.name=name;
        return this;
    }
    public RegistrationServiceRequest setSurname(String surname){
        this.surname=surname;
        return this;
    }
    public RegistrationServiceRequest setUserType(UserType userType){
        this.userType=userType;
        return this;
    }

}
