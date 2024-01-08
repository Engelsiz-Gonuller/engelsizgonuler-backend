package dev.melis.engelsizgonuller.services.model.helpassistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.melis.engelsizgonuller.services.model.category.Category;
import dev.melis.engelsizgonuller.services.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "assistanceRequests")
@Getter
@Setter
public class AssistanceRequests {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "requestId")
    private  long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid",nullable = false)
    @JsonIgnore
    private User user;
    @Column(name = "helpRequesttype")
    private RequestType requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "categoryid",nullable = false)
    @JsonIgnore
    private Category category;

    @Column(name = "requestcontent")
    private String requestContent;
    @Column(name = "requestdate")
    private LocalDate requestDate;
    @Column(name = "requestdeadline")
    @Future
    private LocalDate requestDeadline;
    @Column(name = "isfulfilled")
    private boolean isFulFilled=false;

    public AssistanceRequests(){
        this.requestId=0L;
    }
}
