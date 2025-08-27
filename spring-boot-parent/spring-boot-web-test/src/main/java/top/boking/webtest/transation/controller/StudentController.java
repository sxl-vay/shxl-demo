package top.boking.webtest.transation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.boking.webtest.transation.common.Result;
import top.boking.webtest.transation.domain.Student;
import top.boking.webtest.transation.service.StudentService;

import java.util.List;

/**
 * 学生信息管理控制器
 * @author shxl
 * @date 2024/01/01
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生列表
     */
    @GetMapping("/list")
    public Result<Page<Student>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long classId) {
        
        Page<Student> page = new Page<>(current, size);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        
        if (name != null && !name.trim().isEmpty()) {
            queryWrapper.like("name", name);
        }
        if (classId != null) {
            queryWrapper.eq("class_id", classId);
        }
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("create_time");
        
        Page<Student> result = studentService.page(page, queryWrapper);
        
        return Result.success("查询成功", result);
    }

    /**
     * 根据ID查询学生信息
     */
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        
        if (student != null) {
            return Result.success("查询成功", student);
        } else {
            return Result.error(404, "学生不存在");
        }
    }

    /**
     * 新增学生
     */
    @PostMapping
    public Result<String> save(@RequestBody Student student) {
        boolean result = studentService.save(student);
        
        if (result) {
            return Result.success("新增成功", "操作成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 更新学生信息
     */
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        boolean result = studentService.updateById(student);
        
        if (result) {
            return Result.success("更新成功", "操作成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除学生（逻辑删除）
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        Student student = new Student();
        student.setId(id);
        student.setIsDeleted(1);
        boolean result = studentService.updateById(student);
        
        if (result) {
            return Result.success("删除成功", "操作成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 查询所有学生（不分页）
     */
    @GetMapping("/all")
    public Result<List<Student>> getAll() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByAsc("name");
        
        List<Student> list = studentService.list(queryWrapper);
        
        return Result.success("查询成功", list);
    }

    /**
     * 根据班级ID查询学生列表
     */
    @GetMapping("/class/{classId}")
    public Result<List<Student>> getByClassId(@PathVariable Long classId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", classId);
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByAsc("student_no");
        
        List<Student> list = studentService.list(queryWrapper);
        
        return Result.success("查询成功", list);
    }

    /**
     * 根据学号查询学生
     */
    @GetMapping("/no/{studentNo}")
    public Result<Student> getByStudentNo(@PathVariable String studentNo) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_no", studentNo);
        queryWrapper.eq("is_deleted", 0);
        
        Student student = studentService.getOne(queryWrapper);
        
        if (student != null) {
            return Result.success("查询成功", student);
        } else {
            return Result.error(404, "学生不存在");
        }
    }
} 