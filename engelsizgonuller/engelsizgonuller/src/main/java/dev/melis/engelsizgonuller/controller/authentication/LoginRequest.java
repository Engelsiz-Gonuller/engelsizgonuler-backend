package dev.melis.engelsizgonuller.controller.authentication;

import dev.melis.engelsizgonuller.services.authentication.AuthenticationServiceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(

        @Email
        @NotBlank
        String email,

        @Length(min=10)
        @NotBlank
        String password
) {
    AuthenticationServiceRequest toAuthServiceRequest(){
        return new AuthenticationServiceRequest()
                .setEmail(email)
                .setPassword(password);
    }
}
