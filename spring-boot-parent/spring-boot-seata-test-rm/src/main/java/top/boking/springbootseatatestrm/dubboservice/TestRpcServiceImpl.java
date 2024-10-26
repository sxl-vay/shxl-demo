package top.boking.springbootseatatestrm.dubboservice;

import org.apache.dubbo.config.annotation.DubboService;
import top.boking.dubbo.TestRpc;

/**
 * @Author shxl
 * @Date 2024/10/26 18:16
 * @Version 1.0
 */
@DubboService(group = "groupRm")
public class TestRpcServiceImpl implements TestRpc {
    @Override
    public String simpleRpc(String str) {
        return "rm rpc result:"+str;
    }
}
