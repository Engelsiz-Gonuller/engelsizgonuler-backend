package dev.melis.engelsizgonuller.services.assistancerequest;

import dev.melis.engelsizgonuller.business.result.CreationResult;
import dev.melis.engelsizgonuller.repository.AssistanceRequestRepository;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultAssistanceRequestService implements AssistanceRequestService {

    private final AssistanceRequestRepository assistanceRequestRepository;

    public DefaultAssistanceRequestService(AssistanceRequestRepository assistanceRequestRepository) {
        this.assistanceRequestRepository = assistanceRequestRepository;
    }
    @Override
    public CreationResult createAssistanceRequest(AssistanceRequestServiceRequest assistanceRequests) {
        var assistanceRequest= new AssistanceRequests();
        assistanceRequest.setUser(assistanceRequests.getUser());
        assistanceRequest.setRequestContent(assistanceRequests.getRequestContent());
        assistanceRequest.setRequestDate(LocalDate.now());
        assistanceRequest.setCategory(assistanceRequest.getCategory());
        assistanceRequest.setRequestDeadline(assistanceRequests.getRequestDeadline());
        assistanceRequest.setFulFilled(assistanceRequests.isFulFilled());
        if(assistanceRequests.getRequestType()==RequestType.VOLUNTEER_HELP){
            assistanceRequest.setRequestType(RequestType.VOLUNTEER_HELP);
        }
        assistanceRequest.setRequestType(RequestType.DISABLED_REQUEST);
        assistanceRequestRepository.save(assistanceRequest);
        return CreationResult.success();
    }

    @Override
    public boolean delete(Long id) {
        Optional<AssistanceRequests> existsRequest= assistanceRequestRepository.findById(id);
        if(existsRequest.isPresent()){
            assistanceRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AssistanceRequests> listRequestWithCategory(Long id) {
        List<AssistanceRequests> assistanceRequestsList= (List<AssistanceRequests>) assistanceRequestRepository.findByCategory(id);
        if(assistanceRequestsList!=null && !assistanceRequestsList.isEmpty()){
            return assistanceRequestsList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<AssistanceRequests> listRequestWithRequestType(RequestType requestType) {
        List<AssistanceRequests> assistanceRequestsList= assistanceRequestRepository.findByRequestType(requestType);
        if(assistanceRequestsList!=null && !assistanceRequestsList.isEmpty()){
            return assistanceRequestsList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<AssistanceRequests> listAll() {
        List<AssistanceRequests> assistanceRequestsList= assistanceRequestRepository.findAll();
        if(assistanceRequestsList!=null && !assistanceRequestsList.isEmpty()){
         return assistanceRequestsList;
        }
        return new ArrayList<>();
    }
}
