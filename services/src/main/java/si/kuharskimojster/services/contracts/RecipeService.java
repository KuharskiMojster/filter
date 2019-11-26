package si.kuharskimojster.services.contracts;

import si.kuharskimojster.entities.RecipeEntity;

import javax.persistence.NoResultException;
import java.util.Collection;

public interface RecipeService {
        Collection<RecipeEntity> getAllRecipes();





}
