package si.kuharskimojster.api.controllers;


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


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1")
public class FilterController {


    @Autowired
    RecipeService recipeService;


    @GetMapping("/recipes/filter")
    public ResponseEntity<ResponseModel> filter(@RequestParam(value = "minTime", required = false) Integer minTime,
                                                @RequestParam(value = "maxTime", required = false) Integer maxTime,
                                                @RequestParam(value = "minQuantity", required = false) Integer minQuantity,
                                                @RequestParam(value = "maxQuantity", required = false) Integer maxQuantity,
                                                @RequestParam(value = "minCalories", required = false) Integer minCalories,
                                                @RequestParam(value = "maxCalories", required = false) Integer maxCalories,
                                                @RequestParam(value = "allergens", required = false) List<Allergen> allergenList,
                                                @RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                                                @RequestParam(value = "meal", required = false) Meal meal,
                                                @RequestParam(value = "typeOfMeal", required = false) TypeOfMeal typeOfMeal) {

        List<RecipeEntity> recipes = recipeService.getAllRecipes();

        if (minTime != null && maxTime != null) {
            recipes = recipes.stream().filter(recipe -> minTime <= recipe.getTime() && recipe.getTime() <= maxTime).collect(Collectors.toList());
        }

        if (minQuantity != null && maxQuantity != null) {
            recipes = recipes.stream().filter(recipe -> minQuantity <= recipe.getQuantity() && recipe.getQuantity() <= maxQuantity).collect(Collectors.toList());
        }

        if (minCalories != null && maxCalories != null) {
            recipes = recipes.stream().filter(recipe -> minCalories <= recipe.getCalories() && recipe.getCalories() <= maxCalories).collect(Collectors.toList());
        }

        if (allergenList != null) {
            recipes = recipes.stream().filter(recipeEntity -> Collections.disjoint(recipeEntity.getAllergens(), allergenList)).collect(Collectors.toList());
        }

        if (difficulty != null) {
            recipes = recipes.stream().filter(recipe -> difficulty.equals(recipe.getDifficulty())).collect(Collectors.toList());
        }

        if (meal != null) {
            recipes = recipes.stream().filter(recipe -> meal.equals(recipe.getMeal())).collect(Collectors.toList());
        }

        if (typeOfMeal != null) {
            recipes = recipes.stream().filter(recipe -> typeOfMeal.equals(recipe.getTypeOfMeal())).collect(Collectors.toList());
        }

        return new ResponseEntity<>(new ResponseModel(recipes, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/test")
    public List<RecipeEntity> get() {
        return recipeService.getAllRecipes();
    }


}
