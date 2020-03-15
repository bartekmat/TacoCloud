package com.tacocloud.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    public Taco() {
    }

    private int id;
    private Date createdAt;
    @NotNull
    @Size(min = 5, message = "Name min size is 5 characters")
    private String taco_name;

    @Size(min = 1, message = "You have to choose at least one ingredient")
    private List<Ingredient> ingredients;
}
