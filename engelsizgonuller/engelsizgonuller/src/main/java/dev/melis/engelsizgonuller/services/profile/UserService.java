package dev.melis.engelsizgonuller.services.profile;

import dev.melis.engelsizgonuller.support.exceptions.UserNotFoundException;
import dev.melis.engelsizgonuller.support.result.UpdateResult;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.validation.annotation.Validated;


@Validated
public interface UserService {
   UpdateResult updateInformation(Long id, UpdateUserProfileServiceRequest informationServiceRequest);

   User getProfileInformation(Long id) throws UserNotFoundException;

   UpdateResult updateUserPassword(Long id,UpdatePasswordServiceRequest password);
}
