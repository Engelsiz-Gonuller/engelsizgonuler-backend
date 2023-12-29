package dev.melis.engelsizgonuller.app.register;

import dev.melis.engelsizgonuller.business.resulthandler.BusinessResultHandler;
import dev.melis.engelsizgonuller.core.registration.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/auth/register")
    ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest){
       var result=registrationService.register(registrationRequest.toServiceRequest());
       if(!result.isSuccess()){
           return BusinessResultHandler.handleFailureReason(result.getReason(),result.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
