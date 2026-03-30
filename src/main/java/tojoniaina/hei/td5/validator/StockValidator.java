package tojoniaina.hei.td5.validator;

import org.springframework.stereotype.Component;
import tojoniaina.hei.td5.exception.BadRequestException;

import java.time.Instant;

@Component
public class StockValidator {
    public void validate(Instant at, String unit) {
        if (at == null || unit == null) {
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided.");
        }
    }
}
