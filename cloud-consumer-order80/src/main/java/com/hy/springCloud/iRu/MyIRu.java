package com.hy.springCloud.iRu;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyIRu implements LoadBalancer{

    //当前是第几次请求
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //根据当前请求次数决定调用哪个服务-----轮训算法
    public final int getAndIncrement(){
        //当前请求次数
        int current;
        //下一次是第几次请求
        int next;

        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current+1;
        }while (!atomicInteger.compareAndSet(current,next));//compareAndSet方法类似于线程锁
        return next;
    }


    @Override
    public ServiceInstance service(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
