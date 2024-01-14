package dev.melis.engelsizgonuller.services.assistancerequest;

import lombok.Getter;

@Getter
public class HandleAssistanceRequestServiceRequest {

    private Long id;
    public HandleAssistanceRequestServiceRequest setId(Long id){
        this.id=id;
        return this;
    }
}
