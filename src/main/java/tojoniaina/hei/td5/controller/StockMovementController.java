package tojoniaina.hei.td5.controller;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

import tojoniaina.hei.td5.Entity.*;
import tojoniaina.hei.td5.service.StockMovementService;

@RestController
@RequestMapping("/ingredients")
public class StockMovementController {

    private final StockMovementService service;

    public StockMovementController(StockMovementService service) {
        this.service = service;
    }

    @GetMapping("/{id}/stockMovements")
    public List<StockMovementEntity> getMovements(
            @PathVariable(required = false) Integer id,
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to
    ) {
        return service.getMovements(id, from, to);
    }

    @PostMapping("/{id}/stockMovements")
    public List<StockMovementEntity> create(
            @PathVariable(required = false) Integer id,
            @RequestBody(required = false) List<CreateStockMovement> movements
    ) {
        return service.createMovements(id, movements);
    }
}