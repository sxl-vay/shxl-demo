package top.boking.springbootseatatesttm.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import top.boking.dubbo.TestRpc;

/**
 * @Author shxl
 * @Date 2024/10/26 18:48
 * @Version 1.0
 */
@Service
@Slf4j
public class SeataService {
    @DubboReference(group = "groupRm")
    private TestRpc testRpc;

    public String rpcGet(String name) {
        String resultName = testRpc.simpleRpc(name);
        log.info("rpcGet resultInfo:{}",resultName);
        return resultName;
    }
}
