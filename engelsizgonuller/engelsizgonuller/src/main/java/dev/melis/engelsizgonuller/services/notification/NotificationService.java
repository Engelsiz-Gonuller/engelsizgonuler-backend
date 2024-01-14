package dev.melis.engelsizgonuller.services.notification;

import dev.melis.engelsizgonuller.services.model.notification.Notification;
import dev.melis.engelsizgonuller.support.result.CreationResult;

public interface NotificationService {
    CreationResult saveNotification(NotificationServiceRequest serviceRequest);
}
