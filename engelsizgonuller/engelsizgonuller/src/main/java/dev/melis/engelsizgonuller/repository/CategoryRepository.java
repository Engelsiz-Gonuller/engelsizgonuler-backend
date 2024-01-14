package dev.melis.engelsizgonuller.repository;

import dev.melis.engelsizgonuller.services.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryId(Long id);
}
