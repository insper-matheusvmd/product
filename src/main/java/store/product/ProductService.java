package store.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductOut create(ProductIn in) {
        ProductModel model = new ProductModel();
        model.setName(in.name().trim());
        model.setDescription(in.description().trim());
        model.setUnit(in.unit().trim());
        model.setPrice(scale(in.price()));
        model.setStock(in.stock());
        return toOut(repository.save(model));
    }

    public List<ProductOut> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"))
            .stream()
            .map(this::toOut)
            .toList();
    }

    public ProductOut findById(String id) {
        return toOut(getExisting(id));
    }

    public void delete(String id) {
        ProductModel model = getExisting(id);
        repository.delete(model);
    }

    private ProductModel getExisting(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    private ProductOut toOut(ProductModel model) {
        return new ProductOut(
            model.getId(),
            model.getName(),
            model.getDescription(),
            scale(model.getPrice()),
            model.getStock(),
            model.getUnit()
        );
    }

    private BigDecimal scale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

}
