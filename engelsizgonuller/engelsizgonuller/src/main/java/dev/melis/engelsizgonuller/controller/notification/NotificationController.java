package dev.melis.engelsizgonuller.controller.notification;

import dev.melis.engelsizgonuller.config.UserSession;
import dev.melis.engelsizgonuller.services.notification.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping
    public ResponseEntity<?> getNotification(UserSession session){
        return ResponseEntity.ok(notificationService.getAllNotification(session.id()));
    }
}
