package si.kuharskimojster.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import si.kuharskimojster.utils.Allergen;
import si.kuharskimojster.utils.Difficulty;
import si.kuharskimojster.utils.Meal;
import si.kuharskimojster.utils.TypeOfMeal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="recipes_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicUpdate
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="time", nullable = false)
    private Integer time;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="instructions", nullable = false)
    @Type(type="text")
    private String instructions;

    @Column(name="calories")
    private Integer calories;

    @Column(name="tags")
    private String tags;

    @OneToMany(targetEntity = IngredientEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ingredient_id",referencedColumnName="id")
    private List<IngredientEntity> ingredients;

    @ElementCollection(targetClass= Allergen.class)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name="allergens_table")
    @Column(name="allergens")
    List<Allergen> allergens;

    @Column(name="difficulty")
    private Difficulty difficulty;

    @Column(name="meal")
    @Enumerated(EnumType.ORDINAL)
    private Meal meal;

    @Column(name="meal_type")
    @Enumerated(EnumType.ORDINAL)
    private TypeOfMeal typeOfMeal;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public TypeOfMeal getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(TypeOfMeal typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }
}
