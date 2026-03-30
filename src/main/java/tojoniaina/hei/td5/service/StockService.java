package tojoniaina.hei.td5.service;

import org.springframework.stereotype.Service;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.Entity.StockEntity;
import tojoniaina.hei.td5.Entity.StockType;
import tojoniaina.hei.td5.exception.NotFoundException;
import tojoniaina.hei.td5.repository.IngredientRepository;
import tojoniaina.hei.td5.repository.StockRepository;

import java.time.Instant;
import java.util.List;

@Service
public class StockService {
    private final StockRepository repository;
    private final IngredientRepository ingredientRepository;

    public StockService(StockRepository repository, IngredientRepository ingredientRepository) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
    }

    public double getStock(Integer id, Instant at) {
        IngredientEntity ingredient = ingredientRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Ingredient.id=" + id + " is not found")
                );
        List<StockEntity> stocks = repository.findByIngredientIdAndDate(id, at);
        double total = 0;

        for (StockEntity s : stocks) {
            if (StockType.IN.equals(s.getType())) {
                total += s.getQuantite();
            } else if (StockType.OUT.equals(s.getType())) {
                total -= s.getQuantite();
            }
        }

        return total;
    }
}
