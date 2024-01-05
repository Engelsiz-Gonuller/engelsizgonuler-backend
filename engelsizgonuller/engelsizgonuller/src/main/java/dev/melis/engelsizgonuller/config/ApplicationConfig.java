package dev.melis.engelsizgonuller.config;

import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import dev.melis.engelsizgonuller.services.user.UserService;
import dev.melis.engelsizgonuller.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    private final UserService userService;

    public ApplicationConfig(UserRepository userRepository, UserPasswordEncoder userPasswordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
        this.userService = userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(userPasswordEncoder.getEncoder());
        return authProvider;
    }

}
