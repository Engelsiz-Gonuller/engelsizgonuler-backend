package dev.melis.engelsizgonuller.repository;

import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import dev.melis.engelsizgonuller.services.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequests,Long> {
    List<AssistanceRequests> findAll();
    Optional<AssistanceRequests> findById(Long id);
    List<AssistanceRequests> findByRequestType(RequestType requestType);
    List<AssistanceRequests> findAllByCategoryCategoryId(Long id);
    AssistanceRequests save(AssistanceRequests assistanceRequests);
    void deleteById(Long id);
    Optional<AssistanceRequests> findByRequestHeaderAndUser(String requestContent, User userId);
}
