package top.boking.springbootseatatestrm.dubboservice;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import top.boking.seata.tcc.TccActionOne;
import top.boking.springbootseatatestrm.dao.service.PostThumbService;

/**
 * @Author shxl
 * @Date 2024/10/27 14:31
 * @Version 1.0
 */
@DubboService(group = "groupRm")
public class TccServiceImpl implements TccActionOne {
    @Resource
    private PostThumbService postThumbService;

    /**
     * TwoPhaseBusinessAction必须在实现类上时BusinessActionContextParameter传参才能生效
     * @param actionContext
     * @param accountId
     * @return
     */
    @Override
    @TwoPhaseBusinessAction(name = "tccActionOne", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "accountId") String accountId) {
        String xid = actionContext.getXid();
        System.out.println("a:"+accountId);
        int i = 1/0;
        System.out.println("actionContext = " + actionContext);
        return false;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String accounStId = (String) actionContext.getActionContext("accountId");
        System.out.println("commit = " + actionContext);
        System.out.println("commit = " + actionContext);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        Object a = actionContext.getActionContext().get("accountId");
        System.out.println("rollback:a = " + a);
        System.out.println("rollback = " + actionContext);
        return true;
    }
}
