package com.tacocloud.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final  String ingredient_name;
    private final  Type ingredient_type;

    public static enum Type {
        WRAP, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
