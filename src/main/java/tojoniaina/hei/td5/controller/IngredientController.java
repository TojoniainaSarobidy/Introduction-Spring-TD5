package tojoniaina.hei.td5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.exception.IngredientNotFoundException;
import tojoniaina.hei.td5.service.IngredientService;
import tojoniaina.hei.td5.validator.IngredientValidator;

import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientValidator ingredientValidator;

    public IngredientController(IngredientService ingredientService, IngredientValidator ingredientValidator) {
        this.ingredientService = ingredientService;
        this.ingredientValidator = ingredientValidator;
    }

    @GetMapping(value = "/ingredients")
    public List<IngredientEntity> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping(value = "/ingredients/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable(required = false) Integer id) {
        try {
            ingredientValidator.ingredientValidator(id);
            IngredientEntity ingredient = ingredientService.getIngredientById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(ingredient);
        } catch (IngredientNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "text/plain")
                    .body(e.getMessage());
        }
    }
}
