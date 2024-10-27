package top.boking.springbootseatatesttm.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.boking.springbootseatatesttm.dao.domain.Post;
import top.boking.springbootseatatesttm.service.PostService;

import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/10/26 20:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/seata/tm/post")
public class SeataWithPostController {
    @Resource
    private PostService postService;

    @PutMapping("/updateWithName")
    public String updateWithName(@RequestBody Map<String, Object> name) {
        return postService.updateWithName(String.valueOf(name.get("name")));
    }

    @PostMapping("add")
    public String add(@RequestBody Post post) {
        boolean save = postService.save(post);
        return "ok";
    }

}