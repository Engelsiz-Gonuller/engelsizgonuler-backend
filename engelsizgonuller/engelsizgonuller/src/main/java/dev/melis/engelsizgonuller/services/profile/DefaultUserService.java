package dev.melis.engelsizgonuller.services.profile;

import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import dev.melis.engelsizgonuller.support.exceptions.UserNotFoundException;
import dev.melis.engelsizgonuller.support.result.OperationFailureReason;
import dev.melis.engelsizgonuller.support.result.UpdateResult;
import dev.melis.engelsizgonuller.repository.UserRepository;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserPasswordEncoder encoder;
    public DefaultUserService(UserRepository userRepository, UserPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    @Override
    public UpdateResult updateInformation(Long id, UpdateUserProfileServiceRequest informationServiceRequest) {
        Optional<User> userOptional= userRepository.findById(id);
        if(userOptional.isPresent()){
            var userFromDb=userOptional.get();
            replaceUserInformation(informationServiceRequest, userFromDb);
            userRepository.save(userFromDb);
            return UpdateResult.success();
        }
        return UpdateResult.failure(OperationFailureReason.NOT_FOUND,"user not found");
    }

    private void replaceUserInformation(UpdateUserProfileServiceRequest informationServiceRequest, User userFromDb) {
        if(!ObjectUtils.isEmpty(informationServiceRequest.getName())){
            userFromDb.setName(informationServiceRequest.getName());
        }
        if(!ObjectUtils.isEmpty(informationServiceRequest.getAboutUser())){
            userFromDb.setAboutUser(informationServiceRequest.getAboutUser());
        }
        if(!ObjectUtils.isEmpty(informationServiceRequest.getBirthday())){
            userFromDb.setBirthday(informationServiceRequest.getBirthday());
        }
        if(!ObjectUtils.isEmpty(informationServiceRequest.getUserJob())){
            userFromDb.setUserJob(informationServiceRequest.getUserJob());
        }
        if(!ObjectUtils.isEmpty(informationServiceRequest.getSurname())){
            userFromDb.setUserSurname(informationServiceRequest.getSurname());
        }
    }

    @Override
    public User getProfileInformation(Long id) throws UserNotFoundException {
       Optional<User> user= userRepository.findById(id);
       if(user.isPresent()){
           return user.get();
       }
       throw new UserNotFoundException("User not found");
    }

    @Override
    public UpdateResult updateUserPassword(Long id, UpdatePasswordServiceRequest password) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isPresent()){
            User userFromDb=userOptional.get();
            if(encoder.matches(password.getOldPassword(),userFromDb.getPassword())){
               userFromDb.setUserPassword(encoder.encode(password.getNewPassword()));
               userRepository.save(userFromDb);
               return UpdateResult.success();
            }
            return UpdateResult.failure(OperationFailureReason.UNAUTHORIZED,"old password is incorrect");
        }
        return UpdateResult.failure(OperationFailureReason.NOT_FOUND,"user not found");
    }
}
