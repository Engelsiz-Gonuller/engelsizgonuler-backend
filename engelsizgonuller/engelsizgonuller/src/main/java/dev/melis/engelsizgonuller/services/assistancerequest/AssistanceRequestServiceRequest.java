package dev.melis.engelsizgonuller.services.assistancerequest;

import dev.melis.engelsizgonuller.services.model.category.Category;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.services.model.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class AssistanceRequestServiceRequest {

    private User user;
    private RequestType requestType;
    private Category category;
    private String requestHeader;
    private String requestContent;
    private LocalDate requestDate;
    private LocalDate requestDeadline;
    private boolean isFulFilled;

   public AssistanceRequestServiceRequest setUser(User user){
       this.user=user;
       return this;
   }
    public AssistanceRequestServiceRequest setRequestType(RequestType requestType){
        this.requestType=requestType;
        return this;
    }
    public AssistanceRequestServiceRequest setCategory(Category category){
        this.category=category;
        return this;
    }
    public AssistanceRequestServiceRequest setRequestContent(String requestContent){
        this.requestContent=requestContent;
        return this;
    }
    public AssistanceRequestServiceRequest setRequestHeader(String requestHeader){
        this.requestHeader=requestHeader;
        return this;
    }
    public AssistanceRequestServiceRequest setRequestDate(LocalDate requestDate){
        this.requestDate=requestDate;
        return this;
    }
    public AssistanceRequestServiceRequest setRequestDeadline(LocalDate requestDeadline){
        this.requestDeadline=requestDeadline;
        return this;
    }
    public AssistanceRequestServiceRequest setIsFilled(Boolean isFilled){
        this.isFulFilled=false;
        return this;
    }

}
