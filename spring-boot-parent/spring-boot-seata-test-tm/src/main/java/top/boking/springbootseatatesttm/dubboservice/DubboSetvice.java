package top.boking.springbootseatatesttm.dubboservice;

import org.apache.dubbo.config.annotation.DubboService;
import top.boking.dubbo.TestRpc;

/**
 * @Author shxl
 * @Date 2024/10/26 17:51
 * @Version 1.0
 */
public class DubboSetvice implements TestRpc {
    @Override
    public String simpleRpc(String str) {

        return str+"111";
    }
}
