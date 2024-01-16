package dev.melis.engelsizgonuller.repository;

import dev.melis.engelsizgonuller.services.model.notification.Notification;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUser(User user);
}
