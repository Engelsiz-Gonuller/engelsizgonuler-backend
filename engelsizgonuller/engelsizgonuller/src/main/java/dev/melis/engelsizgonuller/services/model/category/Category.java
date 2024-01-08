package dev.melis.engelsizgonuller.services.model.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @Column(name = "categoryname")
    private String categoryName;

    public Category(){
        this.categoryId=0l;
    }
}
