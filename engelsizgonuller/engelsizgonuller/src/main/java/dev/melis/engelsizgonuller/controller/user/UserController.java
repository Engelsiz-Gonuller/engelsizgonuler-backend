package dev.melis.engelsizgonuller.controller.user;

import dev.melis.engelsizgonuller.config.UserSession;
import dev.melis.engelsizgonuller.services.profile.UserService;
import dev.melis.engelsizgonuller.support.exceptions.UserNotFoundException;
import dev.melis.engelsizgonuller.support.resulthandler.BusinessResultHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> updateUserInformation(UserSession session,@RequestBody UpdateUserProfileRequest request){
       var result= userService.updateInformation(session.id(), request.toServiceRequest());
       if(result.isSuccess()){
           return new ResponseEntity<>(HttpStatus.OK);
       }

       return BusinessResultHandler.handleFailureReason(result.getReason(), result.getMessage());
    }
    @GetMapping("/me")
    public ResponseEntity<?> getUserInformation(UserSession session) throws UserNotFoundException {
        var result=userService.getProfileInformation(session.id());
        if(result!=null){
            return ResponseEntity.ok(new UserProfilePayload(result));
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/password")
    public ResponseEntity<?> updateUserPassword(UserSession session,@RequestBody @Valid UpdateUserPasswordRequest request){
        var result=userService.updateUserPassword(session.id(), request.toServiceRequest());
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return BusinessResultHandler.handleFailureReason(result.getReason(),result.getMessage());
    }

}
