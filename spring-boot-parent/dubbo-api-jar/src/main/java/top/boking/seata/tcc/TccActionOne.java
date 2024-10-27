package top.boking.seata.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface TccActionOne {
    @TwoPhaseBusinessAction(name = "tccActionOne", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, String a);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}