package dev.melis.engelsizgonuller.controller.register;

import dev.melis.engelsizgonuller.business.resulthandler.BusinessResultHandler;
import dev.melis.engelsizgonuller.services.registration.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/auth/register")
    ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest registrationRequest){
       var result=registrationService.register(registrationRequest.toServiceRequest());
       if(!result.isSuccess()){
           return BusinessResultHandler.handleFailureReason(result.getReason(),result.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
