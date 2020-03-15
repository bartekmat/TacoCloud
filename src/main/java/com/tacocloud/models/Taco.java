package com.tacocloud.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt;

    @NotNull
    @Size(min = 5, message = "Name min size is 5 characters")
    private String taco_name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You have to choose at least one ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    private void createAt(){
        this.createdAt = LocalDateTime.now();
    }
}
