package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.services.assistancerequest.AssistanceRequestService;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assistance-request")
public class AssistanceRequestController {

    private final AssistanceRequestService assistanceRequestService;

    public AssistanceRequestController(AssistanceRequestService assistanceRequestService) {
        this.assistanceRequestService = assistanceRequestService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAssistanceRequest(@RequestBody AssistanceRequest assistanceRequest){
        var result=assistanceRequestService.createAssistanceRequest(assistanceRequest.toServiceRequest());
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to create assistance request: ", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAssistanceRequest(@PathVariable Long id){
        var result=assistanceRequestService.delete(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("couldn't find with this id any assistance request",HttpStatus.CONFLICT);
    }

    @GetMapping("/listwithcategory/{id}")
    public ResponseEntity<?> listAssistanceRequestWithCategory(@PathVariable Long id){
        List<AssistanceRequests> assistanceRequestsList= assistanceRequestService.listRequestWithCategory(id);
        if(assistanceRequestsList.isEmpty()){
            return new ResponseEntity<>("No assistance requests found for the specified category", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assistanceRequestsList,HttpStatus.OK);
    }

    @GetMapping("/listwithrequesttype")
    public ResponseEntity<?> listAssistanceWithRequestType(String requestType){
        List<AssistanceRequests> assistanceRequestsList=assistanceRequestService.listRequestWithRequestType(RequestType.valueOf(requestType));
        if(assistanceRequestsList.isEmpty()){
            return new ResponseEntity<>("No assistance requests found for the specified request type", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assistanceRequestsList,HttpStatus.OK);
    }
}
