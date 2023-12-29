package dev.melis.engelsizgonuller.app.register;

import dev.melis.engelsizgonuller.core.dto.UserType;
import dev.melis.engelsizgonuller.core.registration.RegistrationServiceRequest;

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
