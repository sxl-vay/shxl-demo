package top.boking.webtest.transation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.boking.webtest.transation.domain.Student;
import top.boking.webtest.transation.mapper.StudentMapper;
import top.boking.webtest.transation.service.StudentService;

/**
 * 学生信息Service实现类
 * @author shxl
 * @date 2024/01/01
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
} 