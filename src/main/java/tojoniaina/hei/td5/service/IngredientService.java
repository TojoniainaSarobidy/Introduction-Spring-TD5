package tojoniaina.hei.td5.service;

import org.springframework.stereotype.Service;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.exception.IngredientNotFoundException;
import tojoniaina.hei.td5.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientEntity> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public IngredientEntity getIngredientById(Integer id) throws IngredientNotFoundException {
        try {
            Optional<IngredientEntity> ingredient = ingredientRepository.findById(id);
            return ingredient.get();
        } catch (Exception e) {
            throw new IngredientNotFoundException("Ingredient.id=" + id + " is not found");
        }
    }
}
