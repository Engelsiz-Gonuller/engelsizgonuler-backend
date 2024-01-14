package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.config.UserSession;
import dev.melis.engelsizgonuller.services.assistancerequest.AssistanceRequestHandlerService;
import dev.melis.engelsizgonuller.support.resulthandler.BusinessResultHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demand")
public class HandleAssistanceRequestController {

    private final AssistanceRequestHandlerService service;

    public HandleAssistanceRequestController(AssistanceRequestHandlerService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> demandAssistanceRequest(@RequestBody HandleAssistanceRequest request, UserSession userSession){
        var result=service.demandToAssistanceRequest(request.toServiceRequest(),userSession.user());
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return BusinessResultHandler.handleFailureReason(result.getReason(), result.getMessage());
    }
}
