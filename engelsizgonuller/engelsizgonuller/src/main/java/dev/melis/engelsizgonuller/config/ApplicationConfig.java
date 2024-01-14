package dev.melis.engelsizgonuller.config;

import dev.melis.engelsizgonuller.config.session.SessionArgumentResolver;
import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import dev.melis.engelsizgonuller.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;


    public ApplicationConfig(UserRepository userRepository, UserPasswordEncoder userPasswordEncoder) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(userPasswordEncoder.getEncoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(new SessionArgumentResolver());
    }

}
