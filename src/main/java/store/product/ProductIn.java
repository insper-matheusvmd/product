package store.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductIn(
    @NotBlank(message = "name is required")
    String name,

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "price must be greater than zero")
    BigDecimal price,

    @NotBlank(message = "unit is required")
    String unit
) {
}
