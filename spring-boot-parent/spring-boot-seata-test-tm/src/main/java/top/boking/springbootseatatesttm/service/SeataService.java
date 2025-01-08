package top.boking.springbootseatatesttm.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import top.boking.dubbo.TestRpc;
import top.boking.request.DubboRequest;
import top.boking.response.DubboResponse;
import top.boking.seata.tcc.TccActionOne;
import top.boking.springbootseatatesttm.dao.domain.Post;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/10/26 18:48
 * @Version 1.0
 */
@Service
@Slf4j
public class SeataService {
    @DubboReference(group = "groupRm",loadbalance = "roundrobin")
    private TestRpc testRpc;

    @DubboReference(group = "groupRm")
//    @Resource
    private TccActionOne tccActionOne;

    @DubboReference(group = "groupRm2")
//    @Resource
    private TccActionOne tccActionOne2;

    @Resource
    private PostService postService;

    @GlobalTransactional
    public String testSeata(Post post) {
        boolean save = postService.updateById(post);
        if (save) {
            DubboRequest<Map<String,Object>> request = new DubboRequest<>();
            HashMap<String, Object> params = new HashMap<>();
            params.put("postId",post.getId());
            params.put("userId",post.getUserId());
            request.setParams(params);
            DubboResponse<Map<String, Object>> mapDubboResponse = testRpc.rpcTemplate(request);
            return "all finish";
        }
        return "error";
    }


    @GlobalTransactional
    public String tcc(Post post) {
        BusinessActionContext actionContext = new BusinessActionContext();

        DubboRequest<Map<String,Object>> request = new DubboRequest<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("postId",post.getId());
        params.put("userId",post.getUserId());
        request.setParams(params);
        DubboResponse<Map<String, Object>> mapDubboResponse = testRpc.rpcTemplate(request);
        tccActionOne2.prepare(actionContext,post.getTitle());
        return "error";
    }


    public String rpcGet(String name) {
        String resultName = testRpc.simpleRpc(name);
        log.info("rpcGet resultInfo:{}",resultName);
        return resultName;
    }
}
