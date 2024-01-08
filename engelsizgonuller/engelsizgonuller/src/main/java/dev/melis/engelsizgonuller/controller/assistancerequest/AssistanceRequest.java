package dev.melis.engelsizgonuller.controller.assistancerequest;

import dev.melis.engelsizgonuller.services.assistancerequest.AssistanceRequestServiceRequest;
import dev.melis.engelsizgonuller.services.model.category.Category;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.services.model.user.User;

import java.time.LocalDate;

public record AssistanceRequest(
         long userId,
         RequestType requestType,
         long categoryId,
         String requestContent,
         LocalDate requestDeadline,
         boolean isFulFilled
) {
    AssistanceRequestServiceRequest toServiceRequest(){
        User user=new User();
        user.setId(userId);
        Category category=new Category();
        category.setCategoryId(categoryId);
        return new AssistanceRequestServiceRequest()
                .setRequestType(requestType)
                .setRequestContent(requestContent)
                .setRequestDeadline(requestDeadline)
                .setCategory(category)
                .setUser(user)
                .setIsFilled(isFulFilled);
    }
}
