package top.boking.webtest.transation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.boking.webtest.transation.domain.ShxlClass;
import top.boking.webtest.transation.mapper.ShxlClassMapper;
import top.boking.webtest.transation.service.ShxlClassService;
import top.boking.webtest.transation.service.StudentService;

import java.util.List;

/**
 * 班级信息Service实现类
 *
 * @version 1.0
 * @auther shxl
 * @date 2025/8/27 09:20
 */
@Service
@Slf4j
public class ShxlClassServiceImpl extends ServiceImpl<ShxlClassMapper, ShxlClass> implements ShxlClassService {

    @Autowired
    private StudentService studentService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByClassNo(String classNo) {
        LambdaQueryWrapper<ShxlClass> shxlClassLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shxlClassLambdaQueryWrapper.eq(ShxlClass::getClassNo, classNo);
        ShxlClass shxlClass = getOne(shxlClassLambdaQueryWrapper);
        if (shxlClass == null) {
            log.info("班级不存在");
            return;
        }
        removeById(shxlClass.getId());
        studentService.removeByClassId(shxlClass.getId());
//        int i = 1 / 0;
        System.out.println("删除成功");
    }
}