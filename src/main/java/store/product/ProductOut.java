package store.product;

import java.math.BigDecimal;

public record ProductOut(
    String id,
    String name,
    BigDecimal price,
    String unit
) {
}
