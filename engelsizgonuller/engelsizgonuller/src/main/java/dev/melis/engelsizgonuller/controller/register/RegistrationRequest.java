package dev.melis.engelsizgonuller.controller.register;

import dev.melis.engelsizgonuller.services.model.user.UserType;
import dev.melis.engelsizgonuller.services.registration.RegistrationServiceRequest;

import java.time.LocalDate;

public record RegistrationRequest(
        String name,
        String surname,
        String password,
        String email,
        UserType userType

) {
    RegistrationServiceRequest toServiceRequest(){
        return new RegistrationServiceRequest()
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setPassword(password)
                .setUserType(userType);
    }
}
