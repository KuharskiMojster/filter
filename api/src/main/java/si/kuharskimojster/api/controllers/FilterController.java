package si.kuharskimojster.api.controllers;


import com.sun.istack.Nullable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.services.contracts.RecipeService;
import si.kuharskimojster.utils.Allergen;
import si.kuharskimojster.utils.Difficulty;
import si.kuharskimojster.utils.Meal;
import si.kuharskimojster.utils.TypeOfMeal;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1")
public class FilterController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    RecipeService recipeService;


    @GetMapping("/filter")
    public ResponseEntity<ResponseModel> filter(@RequestParam("minTime") Integer minTime,
                                                @RequestParam("maxTime") Integer maxTime,
                                                @RequestParam("minQuantity") Integer minQuantity,
                                                @RequestParam("maxQuantity") Integer maxQuantity,
                                                @RequestParam("minCalories") Integer minCalories,
                                                @RequestParam("maxCalories") Integer maxCalories,
                                                @RequestParam("allergens") List<Allergen> allergenList,
                                                @RequestParam("difficulty") Difficulty difficulty,
                                                @RequestParam("meal") Meal meal,
                                                @RequestParam("typeOfMeal") TypeOfMeal typeOfMeal) {

        List<RecipeEntity> recipes = (List<RecipeEntity>) recipeService.getAllRecipes();

        if (minTime != null && maxTime != null) {
            recipes = recipes.stream().filter(recipe -> minTime <= recipe.getTime() && recipe.getTime() <= maxTime).collect(Collectors.toList());
        }

        if (minQuantity != null && maxQuantity != null) {
            recipes = recipes.stream().filter(recipe -> minQuantity <= recipe.getQuantity() && recipe.getQuantity() <= maxQuantity).collect(Collectors.toList());
        }

        if (minCalories != null && maxCalories != null) {
            recipes = recipes.stream().filter(recipe -> minCalories <= recipe.getQuantity() && recipe.getQuantity() <= maxCalories).collect(Collectors.toList());
        }

        if (allergenList != null) {
            recipes = recipes.stream().filter(recipeEntity -> Collections.disjoint(recipeEntity.getAllergens(), allergenList)).collect(Collectors.toList());
        }

        if (difficulty != null){
            recipes = recipes.stream().filter(recipe -> difficulty.equals(recipe.getDifficulty())).collect(Collectors.toList());
        }

        if (meal != null){
            recipes = recipes.stream().filter(recipe -> meal.equals(recipe.getMeal())).collect(Collectors.toList());
        }

        if (typeOfMeal != null){
            recipes = recipes.stream().filter(recipe -> typeOfMeal.equals(recipe.getTypeOfMeal())).collect(Collectors.toList());
        }

        return new ResponseEntity<>(new ResponseModel(recipes, HttpStatus.OK.value()), HttpStatus.OK);
    }


}
