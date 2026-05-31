package store.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductIn(
    @NotBlank(message = "name is required")
    String name,

    @NotBlank(message = "description is required")
    String description,

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "price must be greater than zero")
    BigDecimal price,

    @Min(value = 0, message = "stock must be greater than or equal to zero")
    int stock,

    @NotBlank(message = "unit is required")
    String unit
) {
}
