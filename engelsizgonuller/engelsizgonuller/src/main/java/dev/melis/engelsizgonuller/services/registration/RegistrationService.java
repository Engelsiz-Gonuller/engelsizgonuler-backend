package dev.melis.engelsizgonuller.services.registration;

import dev.melis.engelsizgonuller.support.result.CreationResult;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RegistrationService {
  CreationResult register(@Valid RegistrationServiceRequest request);

}
