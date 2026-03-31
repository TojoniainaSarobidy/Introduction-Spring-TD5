package tojoniaina.hei.td5.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import tojoniaina.hei.td5.Entity.*;
import tojoniaina.hei.td5.exception.NotFoundException;
import tojoniaina.hei.td5.repository.*;

@Service
public class StockMovementService {

    private final StockMovementRepository repo;
    private final IngredientRepository ingredientRepository;

    public StockMovementService(StockMovementRepository repo,
                                IngredientRepository ingredientRepository) {
        this.repo = repo;
        this.ingredientRepository = ingredientRepository;
    }

    public List<StockMovementEntity> getMovements(Integer id, Instant from, Instant to) {
        ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient.id=" + id + " is not found"));

        return repo.findByIngredient(id, from, to);
    }

    public List<StockMovementEntity> createMovements(Integer id, List<CreateStockMovement> movements) {
        ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient.id=" + id + " is not found"));

        return repo.saveAll(id, movements);
    }
}
