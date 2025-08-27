package top.boking.webtest.transation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.boking.webtest.transation.domain.Student;

/**
 * 学生信息Service接口
 * @author shxl
 * @date 2024/01/01
 */
public interface StudentService extends IService<Student> {

    void removeByClassId(Long classId);
} 