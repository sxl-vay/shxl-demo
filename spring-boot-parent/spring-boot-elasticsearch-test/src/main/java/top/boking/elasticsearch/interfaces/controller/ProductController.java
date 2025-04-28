package top.boking.elasticsearch.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.boking.elasticsearch.application.service.ProductApplicationService;
import top.boking.elasticsearch.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/es/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductApplicationService productApplicationService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productApplicationService.createProduct(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                request.getCategory()
        );
        return ResponseEntity.ok(product);
    }

    @PostMapping("/batchCreate")
    public ResponseEntity<List<Product>> createProductBatch(@RequestBody List<CreateProductRequest> requests) {
        List<Product> products = productApplicationService.craeteAllProducts(requests.stream().map(CreateProductRequest::getProduct).toList());
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestBody UpdateProductRequest request) {
        Product product = productApplicationService.updateProduct(
                id,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                request.getCategory()
        );
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productApplicationService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Product product = productApplicationService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> listProducts(Pageable pageable) {
        Page<Product> products = productApplicationService.listProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Product>> searchByName(@PathVariable String name) {
        List<Product> products = productApplicationService.searchByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        List<Product> products = productApplicationService.findByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<Product>> findByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<Product> products = productApplicationService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/stock/{minStock}")
    public ResponseEntity<List<Product>> findByMinStock(@PathVariable Integer minStock) {
        List<Product> products = productApplicationService.findByMinStock(minStock);
        return ResponseEntity.ok(products);
    }

    // 请求对象
    public static class CreateProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private String category;

        // Getters
        public String getName() { return name; }
        public String getDescription() { return description; }
        public BigDecimal getPrice() { return price; }
        public Integer getStock() { return stock; }
        public String getCategory() { return category; }

        public Product getProduct() {
            return Product.create(name, description, price, stock, category);
        }
    }

    public static class UpdateProductRequest {
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private String category;

        // Getters
        public String getName() { return name; }
        public String getDescription() { return description; }
        public BigDecimal getPrice() { return price; }
        public Integer getStock() { return stock; }
        public String getCategory() { return category; }
    }
}