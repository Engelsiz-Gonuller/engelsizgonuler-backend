package dev.melis.engelsizgonuller.services.notification;

import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.user.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NotificationServiceRequest {

    private String notificationMessage;
    private User user;
    private AssistanceRequests assistanceRequests;

    public NotificationServiceRequest setNotificationMessage(String message){
        this.notificationMessage=message;
        return this;
    }
    public NotificationServiceRequest setUser(User user){
        this.user=user;
        return this;
    }
    public NotificationServiceRequest setAssistanceRequests(AssistanceRequests assistanceRequests){
        this.assistanceRequests=assistanceRequests;
        return this;
    }
}
