package store.product;

import java.math.BigDecimal;

public record ProductOut(
    String id,
    String name,
    String description,
    BigDecimal price,
    int stock,
    String unit
) {
}
