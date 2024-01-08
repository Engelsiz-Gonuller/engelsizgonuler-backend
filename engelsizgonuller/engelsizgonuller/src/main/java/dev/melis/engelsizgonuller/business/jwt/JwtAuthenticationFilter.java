package dev.melis.engelsizgonuller.business.jwt;

import dev.melis.engelsizgonuller.services.jwt.JwtService;
import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.services.model.user.UserRole;
import dev.melis.engelsizgonuller.services.user.UserService;
import dev.melis.engelsizgonuller.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService,UserService userService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

      String authHeader= request.getHeader("Authorization");
      String token;
      String userEmail;
      if(authHeader==null || !authHeader.startsWith("Bearer ")){
          filterChain.doFilter(request,response);
          return;
      }
      token=authHeader.substring(7);
      userEmail= jwtService.extractUser(token);
      UserRole userRole= UserRole.USER;
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userService.loadUserByUsername(userEmail);
            var user= (User) userDetails;
            if(user.getRole()==UserRole.ADMIN){
                userRole=UserRole.ADMIN;
            }
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken autToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                autToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}













