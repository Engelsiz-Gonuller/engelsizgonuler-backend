package dev.melis.engelsizgonuller.services.profile;

import dev.melis.engelsizgonuller.business.result.UpdateResult;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProfileInformationService {
   UpdateResult updateInformation(Long id, ProfileInformationServiceRequest informationServiceRequest);

   List<User> getProfileInformation(Long id);

}
