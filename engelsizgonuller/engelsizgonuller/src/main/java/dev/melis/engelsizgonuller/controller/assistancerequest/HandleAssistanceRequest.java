package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.services.assistancerequest.HandleAssistanceRequestServiceRequest;

public record HandleAssistanceRequest(
        Long id
) {
    public HandleAssistanceRequestServiceRequest toServiceRequest(){
       return new HandleAssistanceRequestServiceRequest()
               .setId(id);
    }
}
