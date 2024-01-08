package dev.melis.engelsizgonuller.services.assistancerequest;

import dev.melis.engelsizgonuller.business.result.CreationResult;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Validated
public interface AssistanceRequestService {
    CreationResult createAssistanceRequest(AssistanceRequestServiceRequest assistanceRequests);
    boolean delete(Long id);

    List<AssistanceRequests> listRequestWithCategory(Long id);

    List<AssistanceRequests> listRequestWithRequestType(RequestType requestType);
    List<AssistanceRequests> listAll();

}
