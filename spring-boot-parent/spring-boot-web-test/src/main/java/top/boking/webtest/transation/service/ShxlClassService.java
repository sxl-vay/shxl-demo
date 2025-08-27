package top.boking.webtest.transation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.boking.webtest.transation.domain.ShxlClass;

/**
 * 班级信息Service接口
 * @version 1.0
 * @auther shxl
 * @date 2025/8/27 09:20
 */
public interface ShxlClassService extends IService<ShxlClass> {

    void removeByClassNo(String classNo);

} 