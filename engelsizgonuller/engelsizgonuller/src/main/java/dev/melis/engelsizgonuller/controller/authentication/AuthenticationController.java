package dev.melis.engelsizgonuller.controller.authentication;

import dev.melis.engelsizgonuller.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PutMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        var result=authenticationService.authenticate(request.toAuthServiceRequest());
        if(result.isSuccess()){
            return ResponseEntity.ok(Map.of("token",result.getMessage()));
        }
        return new ResponseEntity<>(Map.of("message","Wrong credentials"), HttpStatus.UNAUTHORIZED);
    }
}
