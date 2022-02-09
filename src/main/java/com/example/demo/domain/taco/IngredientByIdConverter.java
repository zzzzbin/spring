package com.example.demo.domain.taco;

import com.example.demo.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.demo.domain.taco.Ingredient.Type;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    @Autowired
    private IngredientRepository ingredientRepository;


    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> byId = ingredientRepository.findById(id);
        return byId.isPresent() ? byId.get() : null;
    }
}
