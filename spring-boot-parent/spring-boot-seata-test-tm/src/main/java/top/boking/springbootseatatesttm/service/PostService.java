package top.boking.springbootseatatesttm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.boking.springbootseatatesttm.dao.domain.Post;
import top.boking.springbootseatatesttm.dao.mapper.PostMapper;

import java.util.Date;
import java.util.List;

/**
 * @Author shxl
 * @Date 2024/10/26 20:27
 * @Version 1.0
 */
@Service
public class PostService extends ServiceImpl<PostMapper, Post> {
    @Transactional(rollbackFor = Exception.class)
    public String updateWithName(String str) {
        QueryWrapper<Post> qwl = new QueryWrapper<>();
        qwl.eq("title",str);
        List<Post> posts = list(qwl);
        if (posts != null && posts.size() > 0) {
            Date nowDate = new Date();
            Post post = posts.getFirst();
            post.setContent(str+"rmupdate"+nowDate);
            post.setUpdateTime(nowDate);
            updateById(post);
            return "update";
        }
        return "no find:"+str;
    }
}
