package top.boking.elasticsearch.infrastructure.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.boking.elasticsearch.domain.model.Product;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    // 根据商品名称搜索
    List<Product> findByNameContaining(String name);

    // 根据类别查询
    List<Product> findByCategory(String category);

    // 根据价格范围查询
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // 根据库存量大于指定值查询
    List<Product> findByStockGreaterThan(Integer minStock);
}