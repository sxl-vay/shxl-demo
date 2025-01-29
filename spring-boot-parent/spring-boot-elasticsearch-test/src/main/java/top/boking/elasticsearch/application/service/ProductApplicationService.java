package top.boking.elasticsearch.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.boking.elasticsearch.domain.model.Product;
import top.boking.elasticsearch.infrastructure.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {
    private final ProductRepository productRepository;

    public Product createProduct(String name, String description, BigDecimal price, Integer stock, String category) {
        Product product = Product.create(name, description, price, stock, category);
        return productRepository.save(product);
    }
    public List<Product> craeteAllProducts(List<Product> productList) {
        Iterable<Product> products = productRepository.saveAll(productList);
        //将 products 转换成 List<Product>
        return StreamSupport.stream(products.spliterator(), false).toList();
    }

    public Product updateProduct(String id, String name, String description, BigDecimal price, Integer stock, String category) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("商品不存在"));

        if (price != null) {
            product.updatePrice(price);
        }
        if (stock != null) {
            product.updateStock(stock - product.getStock());
        }
        product.updateInfo(name, description, category);

        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("商品不存在");
        }
        productRepository.deleteById(id);
    }

    public Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("商品不存在"));
    }

    public Page<Product> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findByMinStock(Integer minStock) {
        return productRepository.findByStockGreaterThan(minStock);
    }
}