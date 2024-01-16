package dev.melis.engelsizgonuller.services.notification;

import dev.melis.engelsizgonuller.services.model.notification.Notification;
import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.support.result.CreationResult;

import java.util.List;

public interface NotificationService {
    CreationResult saveNotification(NotificationServiceRequest serviceRequest);

    List<Notification> getAllNotification(Long id);
}
