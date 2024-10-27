package top.boking.springbootseatatesttm.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.boking.seata.tcc.TccActionOne;

/**
 * @Author shxl
 * @Date 2024/10/27 18:16
 * @Version 1.0
 */
@LocalTCC
@Slf4j
//@Service
public class LocalTccService implements TccActionOne {
    @Override
//    @TwoPhaseBusinessAction(name = "tccActionOne",commitMethod = "commit",rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext actionContext, String a) {
        log.info("shxl:prepare:{},{}",actionContext,a);
        return false;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.info("shxl:commit:{}",actionContext);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        log.info("shxl:rollback:{}",actionContext);
        return false;
    }
}
