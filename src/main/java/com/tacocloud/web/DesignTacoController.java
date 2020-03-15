package com.tacocloud.web;

import com.tacocloud.data.IngredientRepository;
import com.tacocloud.data.TacoRepository;
import com.tacocloud.models.Ingredient;
import com.tacocloud.models.Ingredient.Type;
import com.tacocloud.models.Order;
import com.tacocloud.models.Taco;
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
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        Order order = new Order();
        System.out.println("order created "+order.hashCode());
        return order;
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
//                Arrays.asList(
//                new Ingredient("FLTO", "wheat", Type.WRAP),
//                new Ingredient("CLTO", "corn", Type.WRAP),
//                new Ingredient("GRBF", "ground beef",Type.PROTEIN),
//                new Ingredient("CARN", "meat", Type.PROTEIN),
//                new Ingredient("TMTO", "tomato", Type.VEGGIES),
//                new Ingredient("LETC", "lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "cheddar", Type.CHEESE),
//                new Ingredient("JACK", "monterey jack", Type.CHEESE),
//                new Ingredient("SLSA", "salsa", Type.SAUCE),
//                new Ingredient("SRCR", "sour cream", Type.SAUCE)
//                );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));

        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco tacoDesign, Errors errors, @ModelAttribute(name = "order") Order order){
        if(errors.hasErrors()){
            return "design";
        }
        Taco savedTaco = tacoRepository.save(tacoDesign);
        System.out.println("first model order "+order.hashCode());
        order.addTaco(tacoDesign);
        log.info("processing Taco design: "+tacoDesign);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getIngredient_type().equals(type))
                .collect(Collectors.toList());
    }
}
