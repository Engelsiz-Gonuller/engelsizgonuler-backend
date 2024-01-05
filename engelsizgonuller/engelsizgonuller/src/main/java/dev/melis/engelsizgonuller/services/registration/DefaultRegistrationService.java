package dev.melis.engelsizgonuller.services.registration;

import dev.melis.engelsizgonuller.business.result.CreationResult;
import dev.melis.engelsizgonuller.business.result.OperationFailureReason;
import dev.melis.engelsizgonuller.services.model.User;
import dev.melis.engelsizgonuller.services.model.UserType;
import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import dev.melis.engelsizgonuller.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class DefaultRegistrationService implements RegistrationService {

    private final UserRepository userRepository;
    private final UserPasswordEncoder passwordEncoder;

    public DefaultRegistrationService(UserRepository userRepository, UserPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreationResult register(RegistrationServiceRequest request) {
        Optional<User> userOptional= userRepository.findByUserEmail(request.getEmail());
        if(userOptional.isPresent()){
            return CreationResult.failure(OperationFailureReason.CONFLICT,"User has already registered");
        }
        String hashPassword=passwordEncoder.encodePassword(request.getPassword());
        var user= new User();
        user.setUserName(request.getName());
        user.setUserSurname(request.getSurname());
        user.setUserEmail(request.getEmail());
        user.setUserPassword(hashPassword);
        if(request.getUserType()== UserType.VOLUNTEER){
            user.setUserType(UserType.VOLUNTEER);
        }
        user.setUserType(UserType.DISABLED_INDIVIDUAL);
        user.setDateOfRegistration(LocalDate.now());
        userRepository.save(user);
        return CreationResult.success();
    }
}
