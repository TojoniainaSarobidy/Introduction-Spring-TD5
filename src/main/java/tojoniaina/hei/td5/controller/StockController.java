package tojoniaina.hei.td5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tojoniaina.hei.td5.exception.BadRequestException;
import tojoniaina.hei.td5.service.StockService;
import tojoniaina.hei.td5.validator.StockValidator;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StockController {
    private final StockService service;
    private final StockValidator validator;

    public StockController(StockService service, StockValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping(value = "/ingredients/{id}/stock")
    public Map<String, Object> getStock(@PathVariable(required = false) Integer id, @RequestParam(required = false) Instant at, @RequestParam(required = false) String unit) {
        try {
            validator.validate(at, unit);
            double stock = service.getStock(id, at);
            Map<String, Object> response = new HashMap<>();
            response.put("unit", unit);
            response.put("value", stock);
            return response;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
