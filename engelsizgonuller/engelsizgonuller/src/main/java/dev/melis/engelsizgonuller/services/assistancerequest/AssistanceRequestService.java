package dev.melis.engelsizgonuller.services.assistancerequest;

import dev.melis.engelsizgonuller.services.model.user.UserType;
import dev.melis.engelsizgonuller.support.result.CreationResult;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AssistanceRequestService {
    CreationResult createAssistanceRequest(AssistanceRequestServiceRequest assistanceRequests);
    boolean delete(Long id,Long userId);

    List<AssistanceRequests> listRequestWithCategory(Long id, UserType type);

    List<AssistanceRequests> listRequestWithRequestType(RequestType requestType);
    List<AssistanceRequests> listAll();

}
