package dev.melis.engelsizgonuller.repository;

import dev.melis.engelsizgonuller.services.model.category.Category;
import dev.melis.engelsizgonuller.services.model.helpassistance.AssistanceRequests;
import dev.melis.engelsizgonuller.services.model.helpassistance.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequests,Long> {
    List<AssistanceRequests> findAll();
    Optional<AssistanceRequests> findById(Long id);
    List<AssistanceRequests> findByRequestType(RequestType requestType);
    AssistanceRequests findByCategory(Long id);
    AssistanceRequests save(AssistanceRequests assistanceRequests);
    void deleteById(Long id);

}
