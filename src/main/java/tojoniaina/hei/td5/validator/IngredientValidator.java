package tojoniaina.hei.td5.validator;

import org.springframework.stereotype.Component;
import tojoniaina.hei.td5.exception.IngredientNotFoundException;

@Component
public class IngredientValidator {
    public void ingredientValidator(Integer id) throws IngredientNotFoundException {
        if (id == null || id <= 0) {
            throw new IngredientNotFoundException("Ingredient.id={" + id + "} is not found");
        }
    }
}
