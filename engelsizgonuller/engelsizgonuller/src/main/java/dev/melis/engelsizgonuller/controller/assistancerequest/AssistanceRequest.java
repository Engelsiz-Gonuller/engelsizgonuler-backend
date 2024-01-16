package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.services.assistancerequest.AssistanceRequestServiceRequest;
import dev.melis.engelsizgonuller.services.model.category.Category;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.services.model.user.User;

import java.time.LocalDate;

public record AssistanceRequest(
         RequestType requestType,
         long categoryId,
         String requestHeader,
         String requestContent,
         LocalDate requestDeadline,
         boolean isFulFilled
) {
    AssistanceRequestServiceRequest toServiceRequest(long userId){
        User user=new User();
        user.setUserId(userId);
        Category category=new Category();
        category.setCategoryId(categoryId);
        return new AssistanceRequestServiceRequest()
                .setRequestType(requestType)
                .setRequestHeader(requestHeader)
                .setRequestContent(requestContent)
                .setRequestDeadline(requestDeadline)
                .setCategory(category)
                .setUser(user)
                .setIsFilled(isFulFilled);
    }
}
