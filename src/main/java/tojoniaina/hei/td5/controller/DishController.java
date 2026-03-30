package tojoniaina.hei.td5.controller;

import org.springframework.web.bind.annotation.*;
import tojoniaina.hei.td5.Entity.DishEntity;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping
    public List<DishEntity> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/ingredients")
    public void updateIngredients(
            @PathVariable Integer id,
            @RequestBody(required = false) List<IngredientEntity> ingredients
    ) {
        service.updateIngredients(id, ingredients);
    }
}