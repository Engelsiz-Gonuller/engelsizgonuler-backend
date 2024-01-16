package dev.melis.engelsizgonuller.services.assistancerequest;


import dev.melis.engelsizgonuller.services.model.user.UserType;
import dev.melis.engelsizgonuller.support.result.CreationResult;
import dev.melis.engelsizgonuller.repository.AssistanceRequestRepository;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.support.result.OperationFailureReason;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class DefaultAssistanceRequestService implements AssistanceRequestService {

    private final AssistanceRequestRepository assistanceRequestRepository;

    public DefaultAssistanceRequestService(AssistanceRequestRepository assistanceRequestRepository) {
        this.assistanceRequestRepository = assistanceRequestRepository;
    }
    @Override
    public CreationResult createAssistanceRequest(AssistanceRequestServiceRequest serviceRequest) {
        Optional<AssistanceRequests> requestOptional=assistanceRequestRepository.findByRequestHeaderAndUser(serviceRequest.getRequestHeader(),serviceRequest.getUser());
        if(requestOptional.isPresent()){
            return CreationResult.failure(OperationFailureReason.CONFLICT,"Request already exist");
        }
        var assistanceRequest= new AssistanceRequests();
        assistanceRequest.setUser(serviceRequest.getUser());
        assistanceRequest.setRequestHeader(serviceRequest.getRequestHeader());
        assistanceRequest.setRequestContent(serviceRequest.getRequestContent());
        assistanceRequest.setRequestDate(LocalDate.now());
        assistanceRequest.setCategory(serviceRequest.getCategory());
        assistanceRequest.setRequestDeadline(serviceRequest.getRequestDeadline());
        assistanceRequest.setFulFilled(serviceRequest.isFulFilled());
        if(serviceRequest.getRequestType()==RequestType.VOLUNTEER_HELP){
            assistanceRequest.setRequestType(RequestType.VOLUNTEER_HELP);
        }
        else{
            assistanceRequest.setRequestType(RequestType.DISABLED_REQUEST);
        }
        assistanceRequestRepository.save(assistanceRequest);
        return CreationResult.success();
    }

    @Override
    public boolean delete(Long id,Long userId) {
        Optional<AssistanceRequests> existsRequest= assistanceRequestRepository.findById(id);
        if(existsRequest.isPresent()){
            if(existsRequest.get().getUser().getUserId()!=userId){
                return false;
            }
            assistanceRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public List<AssistanceRequests> listRequestWithCategory(Long id, UserType type) {
        List<AssistanceRequests> assistanceRequestsList= assistanceRequestRepository.findAllByCategoryCategoryId(id);
        if(assistanceRequestsList!=null && !assistanceRequestsList.isEmpty()){
            if (type.equals(UserType.VOLUNTEER)){
                return assistanceRequestsList.stream().filter(requests -> requests.getRequestType().equals(RequestType.DISABLED_REQUEST)).toList();
            }else{
                return assistanceRequestsList.stream().filter(requests -> requests.getRequestType().equals(RequestType.VOLUNTEER_HELP)).toList();
            }
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