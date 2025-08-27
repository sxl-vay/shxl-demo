package top.boking.webtest.transation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.boking.webtest.transation.domain.Student;

/**
 * 学生信息Mapper接口
 * @author shxl
 * @date 2024/01/01
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
} 