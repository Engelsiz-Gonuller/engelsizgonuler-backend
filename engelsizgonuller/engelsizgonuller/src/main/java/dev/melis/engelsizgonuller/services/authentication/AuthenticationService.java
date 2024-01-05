package dev.melis.engelsizgonuller.services.authentication;

import dev.melis.engelsizgonuller.business.result.AuthenticationResult;

public interface AuthenticationService {
    AuthenticationResult authenticate(AuthenticationServiceRequest request);
}
