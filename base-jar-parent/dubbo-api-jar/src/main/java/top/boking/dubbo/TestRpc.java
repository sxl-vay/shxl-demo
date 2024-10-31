package top.boking.dubbo;

import top.boking.request.DubboRequest;
import top.boking.response.DubboResponse;

import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/10/26 17:41
 * @Version 1.0
 */
public interface TestRpc {
    String simpleRpc(String str);
    DubboResponse<Map<String,Object>> rpcTemplate(DubboRequest<Map<String,Object>> request);
}
