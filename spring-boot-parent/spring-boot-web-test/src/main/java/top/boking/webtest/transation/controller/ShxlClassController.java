package top.boking.webtest.transation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.boking.webtest.transation.common.Result;
import top.boking.webtest.transation.domain.ShxlClass;
import top.boking.webtest.transation.service.ShxlClassService;

import java.util.List;

/**
 * 班级信息Controller
 * @version 1.0
 * @auther shxl
 * @date 2025/8/27 09:20
 */
@RestController
@RequestMapping("/api/class")
public class ShxlClassController {

    @Autowired
    private ShxlClassService shxlClassService;

    /**
     * 分页查询班级列表
     */
    @GetMapping("/list")
    public Result<Page<ShxlClass>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        
        Page<ShxlClass> page = new Page<>(current, size);
        QueryWrapper<ShxlClass> queryWrapper = new QueryWrapper<>();
        
        if (name != null && !name.trim().isEmpty()) {
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("create_time");
        
        Page<ShxlClass> result = shxlClassService.page(page, queryWrapper);
        
        return Result.success("查询成功", result);
    }

    /**
     * 根据ID查询班级信息
     */
    @GetMapping("/{id}")
    public Result<ShxlClass> getById(@PathVariable Long id) {
        ShxlClass shxlClass = shxlClassService.getById(id);
        
        if (shxlClass != null) {
            return Result.success("查询成功", shxlClass);
        } else {
            return Result.error(404, "班级不存在");
        }
    }

    /**
     * 新增班级
     */
    @PostMapping
    public Result<String> save(@RequestBody ShxlClass shxlClass) {
        boolean result = shxlClassService.save(shxlClass);
        
        if (result) {
            return Result.success("新增成功", "操作成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 更新班级信息
     */
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody ShxlClass shxlClass) {
        shxlClass.setId(id);
        boolean result = shxlClassService.updateById(shxlClass);
        
        if (result) {
            return Result.success("更新成功", "操作成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除班级（逻辑删除）
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        ShxlClass shxlClass = new ShxlClass();
        shxlClass.setId(id);
        shxlClass.setIsDeleted(1);
        boolean result = shxlClassService.updateById(shxlClass);
        
        if (result) {
            return Result.success("删除成功", "操作成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 查询所有班级（不分页）
     */
    @GetMapping("/all")
    public Result<List<ShxlClass>> getAll() {
        QueryWrapper<ShxlClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByAsc("name");
        
        List<ShxlClass> list = shxlClassService.list(queryWrapper);
        
        return Result.success("查询成功", list);
    }
} 