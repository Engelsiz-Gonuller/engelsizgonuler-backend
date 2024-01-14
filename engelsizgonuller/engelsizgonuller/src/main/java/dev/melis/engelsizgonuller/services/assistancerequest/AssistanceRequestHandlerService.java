package dev.melis.engelsizgonuller.services.assistancerequest;

import dev.melis.engelsizgonuller.repository.AssistanceRequestRepository;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.services.notification.NotificationService;
import dev.melis.engelsizgonuller.services.notification.NotificationServiceRequest;
import dev.melis.engelsizgonuller.support.result.OperationFailureReason;
import dev.melis.engelsizgonuller.support.result.UpdateResult;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssistanceRequestHandlerService {

    private final AssistanceRequestRepository assistanceRequestRepository;
    private final NotificationService notificationService;

    public AssistanceRequestHandlerService(AssistanceRequestRepository assistanceRequestRepository, NotificationService notificationService) {
        this.assistanceRequestRepository = assistanceRequestRepository;
        this.notificationService = notificationService;
    }

    public UpdateResult demandToAssistanceRequest(HandleAssistanceRequestServiceRequest request, User user) {
        Optional<AssistanceRequests> optionalAssistanceRequests= assistanceRequestRepository.findById(request.getId());
        if(optionalAssistanceRequests.isPresent()){
           var assistanceRequests= optionalAssistanceRequests.get();
           if(!assistanceRequests.isFulFilled()){
               assistanceRequests.setFulFilled(true);
               assistanceRequestRepository.save(assistanceRequests);
               createNotification(user, assistanceRequests);
               return UpdateResult.success();
           }else{
               return UpdateResult.failure(OperationFailureReason.ALREADY_REPORTED,"Request has already demanded");
           }
        }
        return UpdateResult.failure(OperationFailureReason.NOT_FOUND,"Request not found");
    }

    private void createNotification(User helper, AssistanceRequests assistanceRequests) {
        NotificationServiceRequest notificationServiceRequest= new NotificationServiceRequest();
        notificationServiceRequest.setAssistanceRequests(assistanceRequests);
        notificationServiceRequest.setUser(assistanceRequests.getUser());
        String message= switch (assistanceRequests.getRequestType()){
            case VOLUNTEER_HELP -> helper.getName()+" "+helper.getUserSurname()+" called help request for "+ assistanceRequests.getRequestHeader()+" Let's help :)";
            case DISABLED_REQUEST -> helper.getName()+" "+ helper.getUserSurname()+" wants to help for your "+ assistanceRequests.getRequestHeader()+ " help request. Let's check it :)";
        };
        notificationServiceRequest.setNotificationMessage(message);
        notificationService.saveNotification(notificationServiceRequest);
    }
}
