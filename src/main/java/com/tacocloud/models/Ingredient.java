package com.tacocloud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {

    @Id
    private String id;
    private String ingredient_name;

    @Enumerated(EnumType.STRING)
    private Type ingredient_type;


    public static enum Type {
        WRAP, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
