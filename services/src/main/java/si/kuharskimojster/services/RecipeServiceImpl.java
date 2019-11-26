package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.kuharskimojster.entities.RecipeEntity;
import si.kuharskimojster.repositories.RecipeRepository;
import si.kuharskimojster.services.contracts.RecipeService;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public Collection<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }


}
