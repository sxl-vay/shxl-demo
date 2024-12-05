package top.boking.springbootseatatesttm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.boking.springbootseatatesttm.dao.domain.Post;

/**
 * @author mac
 * @description 针对表【post(帖子)】的数据库操作Mapper
 * @createDate 2024-10-26 19:55:04
 * @Entity generator.domain.Post
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}




