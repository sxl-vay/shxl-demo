package top.boking.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.elasticsearch.service.ProductDataGeneratorService;

@RestController
@RequiredArgsConstructor
public class DataGeneratorController {
    private final ProductDataGeneratorService generatorService;

    @PostMapping("/api/generate-data")
    public String generateData(@RequestParam(defaultValue = "1000000") int count) {
        long start = System.currentTimeMillis();
        generatorService.generateBulkData(count);
        long end = System.currentTimeMillis();
        return String.format("成功生成 %d 条数据，耗时：%d 秒", count, (end - start) / 1000);
    }
}