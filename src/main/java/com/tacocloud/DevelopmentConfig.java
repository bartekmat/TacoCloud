package com.tacocloud;

import com.tacocloud.data.IngredientRepository;
import com.tacocloud.data.TacoRepository;
import com.tacocloud.data.UserRepository;
import com.tacocloud.models.Ingredient;
import com.tacocloud.models.Ingredient.Type;
import com.tacocloud.models.Taco;
import com.tacocloud.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader (IngredientRepository ingredientRepository,
                                         UserRepository userRepository,
                                         TacoRepository tacoRepository,
                                         PasswordEncoder encoder){
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.MEAT);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.MEAT);
            Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(tomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            userRepository.save(new User("bartek", encoder.encode("pass"),
                    "Bartek Matuszewski", "Szymanowskiego 20", "Gdansk",
                    "80-280", "664893105"));

            Taco taco1 = new Taco();
            taco1.setTaco_name("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setTaco_name("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(groundBeef, cheddar, jack, sourCream, cornTortilla));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setTaco_name("Veg-Out");
            taco3.setIngredients(Arrays.asList(tomatoes,lettuce,salsa,flourTortilla,cornTortilla));
            tacoRepository.save(taco3);
        };
    }
}
