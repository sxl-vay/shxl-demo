package top.boking.elasticsearch.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Getter
@Document(indexName = "products")
public class Product {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Keyword)
    private String category;

    // 领域方法
    public void updateStock(int quantity) {
        if (this.stock + quantity < 0) {
            throw new IllegalArgumentException("库存不足");
        }
        this.stock += quantity;
    }

    public void updatePrice(BigDecimal newPrice) {
        if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("价格必须大于0");
        }
        this.price = newPrice;
    }

    public void updateInfo(String name, String description, String category) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
        if (category != null && !category.trim().isEmpty()) {
            this.category = category;
        }
    }

    // 构造方法
    private Product() {}

    public static Product create(String name, String description, BigDecimal price, Integer stock, String category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("商品名称不能为空");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("商品价格必须大于0");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("商品库存不能为负数");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("商品类别不能为空");
        }

        Product product = new Product();
        product.name = name;
        product.description = description;
        product.price = price;
        product.stock = stock;
        product.category = category;
        return product;
    }
}