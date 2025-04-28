package top.boking.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import top.boking.elasticsearch.domain.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDataGeneratorService {
    private final ElasticsearchOperations operations;
    private final Random random = new Random();

    // 品牌数据
    private final Map<String, List<String>> brandCategories = new HashMap<>() {{
        put("手机", Arrays.asList("Apple", "Samsung", "Huawei", "Xiaomi", "OPPO", "VIVO", "Honor"));
        put("电脑", Arrays.asList("Apple", "Lenovo", "HP", "Dell", "ASUS", "Acer", "Microsoft"));
        put("相机", Arrays.asList("Canon", "Sony", "Nikon", "Fujifilm", "Panasonic", "Olympus"));
        put("耳机", Arrays.asList("Apple", "Sony", "Bose", "Sennheiser", "JBL", "Samsung", "Xiaomi"));
        put("平板", Arrays.asList("Apple", "Samsung", "Huawei", "Lenovo", "Microsoft", "Xiaomi"));
        put("手表", Arrays.asList("Apple", "Samsung", "Huawei", "Xiaomi", "Garmin", "Fitbit"));
    }};

    // 商品特性
    private final Map<String, List<String>> categoryFeatures = new HashMap<>() {{
        put("手机", Arrays.asList("5G", "高刷新率", "快充", "大电池", "NFC", "无线充电", "防水"));
        put("电脑", Arrays.asList("高性能", "轻薄", "长续航", "高分屏", "雷电接口", "背光键盘"));
        put("相机", Arrays.asList("高像素", "防抖", "4K视频", "触摸屏", "WiFi", "蓝牙"));
        put("耳机", Arrays.asList("主动降噪", "无线", "长续航", "快充", "防水", "触控"));
        put("平板", Arrays.asList("手写笔", "键盘", "高分屏", "长续航", "快充", "5G"));
        put("手表", Arrays.asList("心率监测", "血氧", "GPS", "防水", "运动模式", "睡眠监测"));
    }};

    // 价格范围
    private final Map<String, int[]> categoryPriceRanges = new HashMap<>() {{
        put("手机", new int[]{999, 9999});
        put("电脑", new int[]{3999, 29999});
        put("相机", new int[]{1999, 39999});
        put("耳机", new int[]{199, 3999});
        put("平板", new int[]{1999, 12999});
        put("手表", new int[]{799, 5999});
    }};

    public void generateBulkData(int count) {
        // 使用线程池并行生成数据
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int batchSize = 1000;
        int totalBatches = count / batchSize + (count % batchSize > 0 ? 1 : 0);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < totalBatches; i++) {
            int currentBatchSize = Math.min(batchSize, count - i * batchSize);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                List<Product> products = generateBatch(currentBatchSize);
                operations.save(products);
            }, executorService);
            futures.add(future);
        }

        // 等待所有任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
    }

    private List<Product> generateBatch(int size) {
        List<Product> products = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String category = getRandomCategory();
            String brand = getRandomBrand(category);
            products.add(generateProduct(brand, category));
        }
        return products;
    }

    private Product generateProduct(String brand, String category) {
        String name = generateProductName(brand, category);
        String description = generateDescription(category);
        BigDecimal price = generatePrice(category);
        int stock = random.nextInt(1000) + 1;

        return Product.create(name, description, price, stock, category);
    }

    private String generateProductName(String brand, String category) {
        String[] models = {"Pro", "Max", "Plus", "Ultra", "SE", "Elite", "Premium"};
        String model = models[random.nextInt(models.length)];
        int year = 2020 + random.nextInt(4);
        return String.format("%s %s %s %d", brand, category, model, year);
    }

    private String generateDescription(String category) {
        List<String> features = categoryFeatures.get(category);
        // 随机选择3-5个特性
        int featureCount = random.nextInt(3) + 3;
        List<String> selectedFeatures = new ArrayList<>(features);
        Collections.shuffle(selectedFeatures);
        
        return selectedFeatures.stream()
                .limit(featureCount)
                .collect(Collectors.joining("，")) + "。";
    }

    private BigDecimal generatePrice(String category) {
        int[] range = categoryPriceRanges.get(category);
        int price = random.nextInt(range[1] - range[0]) + range[0];
        // 使价格看起来更自然，例如：1999，2999
        price = (price / 100) * 100 + 99;
        return new BigDecimal(price);
    }

    private String getRandomCategory() {
        List<String> categories = new ArrayList<>(brandCategories.keySet());
        return categories.get(random.nextInt(categories.size()));
    }

    private String getRandomBrand(String category) {
        List<String> brands = brandCategories.get(category);
        return brands.get(random.nextInt(brands.size()));
    }
}