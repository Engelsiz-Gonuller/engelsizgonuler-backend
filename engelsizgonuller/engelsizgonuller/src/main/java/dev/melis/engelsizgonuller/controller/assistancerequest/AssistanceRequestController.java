package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.config.UserSession;
import dev.melis.engelsizgonuller.services.assistancerequest.AssistanceRequestService;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.support.resulthandler.BusinessResultHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assistance/requests")
public class AssistanceRequestController {

    private final AssistanceRequestService assistanceRequestService;

    public AssistanceRequestController(AssistanceRequestService assistanceRequestService) {
        this.assistanceRequestService = assistanceRequestService;
    }
    @PostMapping
    public ResponseEntity<?> createAssistanceRequest(UserSession session, @RequestBody AssistanceRequest assistanceRequest){
        var result=assistanceRequestService.createAssistanceRequest(assistanceRequest.toServiceRequest(session.id()));
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return BusinessResultHandler.handleFailureReason(result.getReason(), result.getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssistanceRequest(@PathVariable Long id, UserSession session){
        var result=assistanceRequestService.delete(id, session.id());
        if(result){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("You don't have rights to delete this request",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> listAssistanceRequestWithCategory(UserSession session,@PathVariable Long id){
        List<AssistanceRequests> assistanceRequestsList= assistanceRequestService.listRequestWithCategory(id,session.user().getUserType());
        if(assistanceRequestsList.isEmpty()){
            return new ResponseEntity<>("No assistance requests found for the specified category", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assistanceRequestsList,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> listAssistanceWithRequestType(@RequestParam String requestType){
        List<AssistanceRequests> assistanceRequestsList=assistanceRequestService.listRequestWithRequestType(RequestType.valueOf(requestType));
        if(assistanceRequestsList.isEmpty()){
            return new ResponseEntity<>("No assistance requests found for the specified request type", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assistanceRequestsList,HttpStatus.OK);
    }
}