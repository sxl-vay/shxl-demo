package top.boking.webtest.transation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void removeByClassId(Long classId) {
        this.remove(new QueryWrapper<Student>().eq("class_id", classId));

    }
}