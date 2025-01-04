package top.boking.utils;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

public class ShxlLoacBalance implements LoadBalance {
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        Object[] arguments = invocation.getArguments();
        Object argument = arguments[0];
        if (argument instanceof String) {
            String str = (String) argument;
            if (str.length() > 5) {
                return invokers.get(0);
            }
        }
        return invokers.get(1);
    }
}
