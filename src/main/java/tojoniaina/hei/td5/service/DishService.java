package tojoniaina.hei.td5.service;

import org.springframework.stereotype.Service;
import tojoniaina.hei.td5.Entity.DishEntity;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.exception.NotFoundException;
import tojoniaina.hei.td5.repository.DishRepository;
import tojoniaina.hei.td5.repository.IngredientRepository;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public DishService(DishRepository dishRepository,
                       IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<DishEntity> getAll() {
        return dishRepository.findAll();
    }

    public void updateIngredients(Integer dishId, List<IngredientEntity> ingredients) {
        dishRepository.findById(dishId)
                .orElseThrow(() ->
                        new NotFoundException("Dish.id=" + dishId + " is not found")
                );
        if (ingredients == null) {
            throw new IllegalArgumentException("Ingredient list must not be null");
        }
        List<Integer> validIds = ingredients.stream()
                .map(IngredientEntity::getIdentifiant)
                .filter(id -> ingredientRepository.findById(id).isPresent())
                .toList();
        dishRepository.updateIngredients(dishId, validIds);
    }
}