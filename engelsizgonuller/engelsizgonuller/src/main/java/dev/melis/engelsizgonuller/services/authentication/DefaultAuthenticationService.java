package dev.melis.engelsizgonuller.services.authentication;

import dev.melis.engelsizgonuller.support.result.AuthenticationResult;
import dev.melis.engelsizgonuller.support.result.OperationFailureReason;
import dev.melis.engelsizgonuller.services.jwt.JwtService;
import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import dev.melis.engelsizgonuller.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    private final JwtService jwtService;

    public DefaultAuthenticationService(UserRepository userRepository, UserPasswordEncoder userPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public AuthenticationResult authenticate(AuthenticationServiceRequest request) {
        Optional<User> userOptional= userRepository.findByUserEmail(request.getEmail());
        if(userOptional.isEmpty()){
            return AuthenticationResult.failed(OperationFailureReason.NOT_FOUND,"User not found");
        }
       User user=userOptional.get();
        if(!userPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            return AuthenticationResult.failed(OperationFailureReason.UNAUTHORIZED,"Wrong credential");
        }

        var jwtToken= jwtService.generateToken(user);
        return AuthenticationResult.success(jwtToken);
    }
}
