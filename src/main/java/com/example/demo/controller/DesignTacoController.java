package com.example.demo.controller;

import com.example.demo.domain.taco.Ingredient;
import com.example.demo.domain.taco.Taco;
import com.example.demo.domain.taco.Ingredient.Type;
import com.example.demo.domain.taco.TacoOrder;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private TacoRepository tacoRepository;

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        Iterable<Ingredient> all = ingredientRepository.findAll();
        for (Ingredient ingredient: all){
            ingredients.add(ingredient);
        }
        Type[] types = Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        return "taco/design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder){
        if (errors.hasErrors()){
            return "taco/design";
        }
        Taco saved = tacoRepository.save(taco);
        tacoOrder.addDesign(saved);
        log.info("Processing taco: {}", saved);
        return "redirect:/orders/current";
    }

}
