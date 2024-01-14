package dev.melis.engelsizgonuller.services.model.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "notification")
@Data
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    @Column(name = "message")
    private String notificationMessage;

    @Column(name = "date")
    private LocalDate notificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "requestid", nullable = false)
    @JsonIgnore
    private AssistanceRequests assistanceRequests;

    public Notification() {
        this.notificationId = 0L;
    }
}
