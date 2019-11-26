package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.repositories.RecipeRepository;
import si.kuharskimojster.services.contracts.RecipeService;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }


}
