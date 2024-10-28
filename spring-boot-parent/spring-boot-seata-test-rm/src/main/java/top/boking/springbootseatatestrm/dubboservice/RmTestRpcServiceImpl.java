package top.boking.springbootseatatestrm.dubboservice;

import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import top.boking.dubbo.TestRpc;
import top.boking.request.DubboRequest;
import top.boking.response.DubboResponse;
import top.boking.springbootseatatestrm.dao.PostThumb;
import top.boking.springbootseatatestrm.dao.service.PostThumbService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author shxl
 * @Date 2024/10/26 18:16
 * @Version 1.0
 */
@DubboService(group = "groupRm")
@Slf4j
public class RmTestRpcServiceImpl implements TestRpc {
    @Resource
    private PostThumbService postThumbService;

    @Override
    public String simpleRpc(String str) {
        return "rm rpc result:" + str;
    }

    @Override
    public DubboResponse<Map<String, Object>> rpcTemplate(DubboRequest<Map<String, Object>> request) {

        PostThumb postThumb = new PostThumb();
        Long postId = (Long) request.getParams().get("postId");
        Long userId = (Long) request.getParams().get("userId");
        if (Objects.equals(9L, userId)) {
            int i = 1/0;
        }
        postThumb.setPostId(postId);
        postThumb.setUserId(userId);

        boolean save = postThumbService.save(postThumb);
        if (save) {
            return DubboResponse.success(new HashMap<>());
        }
        log.info("rpcTemplate finish:{}", JSONObject.toJSONString(request));
        return DubboResponse.fail(null);
    }

}
