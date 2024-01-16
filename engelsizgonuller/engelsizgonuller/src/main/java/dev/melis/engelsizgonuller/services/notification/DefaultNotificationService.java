package dev.melis.engelsizgonuller.services.notification;

import dev.melis.engelsizgonuller.repository.NotificationRepository;
import dev.melis.engelsizgonuller.services.model.notification.Notification;
import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.support.result.CreationResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DefaultNotificationService implements NotificationService {

    private final NotificationRepository notificationRepository;

    public DefaultNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public CreationResult saveNotification(NotificationServiceRequest serviceRequest) {
            var notification = new Notification();
            notification.setNotificationMessage(serviceRequest.getNotificationMessage());
            notification.setNotificationDate(LocalDate.now());
            notification.setUser(serviceRequest.getUser());
            notification.setAssistanceRequests(serviceRequest.getAssistanceRequests());
            notificationRepository.save(notification);
            return CreationResult.success();

    }
    @Override
    public List<Notification> getAllNotification(Long id) {
        User user=new User();
        user.setUserId(id);
        return notificationRepository.findAllByUser(user);
    }

}
