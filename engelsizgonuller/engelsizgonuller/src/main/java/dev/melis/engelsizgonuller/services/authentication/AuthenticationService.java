package dev.melis.engelsizgonuller.services.authentication;

import dev.melis.engelsizgonuller.support.result.AuthenticationResult;

public interface AuthenticationService {
    AuthenticationResult authenticate(AuthenticationServiceRequest request);
}
