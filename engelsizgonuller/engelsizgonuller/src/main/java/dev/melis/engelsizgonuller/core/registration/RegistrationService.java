package dev.melis.engelsizgonuller.core.registration;

import dev.melis.engelsizgonuller.business.result.CreationResult;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RegistrationService {
  CreationResult register(@Valid RegistrationServiceRequest request);

}
