package com.tacocloud.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class Ingredient {

    @Id
    private String id;
    private String ingredient_name;

    @Enumerated(EnumType.STRING)
    private Type ingredient_type;

    public Ingredient() {
    }

    public static enum Type {
        WRAP, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
