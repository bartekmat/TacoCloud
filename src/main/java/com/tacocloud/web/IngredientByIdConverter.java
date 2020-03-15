package com.tacocloud.web;


import com.tacocloud.data.IngredientRepository;
import com.tacocloud.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
    public class IngredientByIdConverter implements Converter<String, Ingredient> {

        private IngredientRepository ingredientRepo;

        @Autowired
        public IngredientByIdConverter(IngredientRepository ingredientRepo) {
            this.ingredientRepo = ingredientRepo;
        }

        @Override
        public Ingredient convert(String id) {
            Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
            return optionalIngredient.orElse(null);
        }

    }
