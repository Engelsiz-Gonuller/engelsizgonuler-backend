package dev.melis.engelsizgonuller.services.profile;

import dev.melis.engelsizgonuller.business.result.OperationFailureReason;
import dev.melis.engelsizgonuller.business.result.UpdateResult;
import dev.melis.engelsizgonuller.repository.UserRepository;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultProfileInformationService implements ProfileInformationService{
    private final UserRepository userRepository;

    public DefaultProfileInformationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UpdateResult updateInformation(Long id, ProfileInformationServiceRequest informationServiceRequest) {
        Optional<User> userOptional= userRepository.findById(id);
        if(userOptional.isPresent()){
            var user= new User();
            user.setUserEmail(informationServiceRequest.getEmail());
            user.setUserName(informationServiceRequest.getName());
            user.setUserSurname(informationServiceRequest.getSurname());
            user.setAboutUser(informationServiceRequest.getAboutUser());
            user.setUserAge(calculateAge(informationServiceRequest.getBirthday()));
            user.setBirthday(informationServiceRequest.getBirthday());
            user.setUserJob(informationServiceRequest.getUserJob());
            userRepository.save(user);
            return UpdateResult.success();
        }
        return UpdateResult.failure(OperationFailureReason.NOT_FOUND,"user not found");
    }

    @Override
    public List<User> getProfileInformation(Long id) {
        return null;
    }

    private int calculateAge(LocalDate birthday){
        LocalDate currenDate=LocalDate.now();
        int yearBirthday= birthday.getYear();
        int currentYear= currenDate.getYear();

        return currentYear-yearBirthday;
    }
}
