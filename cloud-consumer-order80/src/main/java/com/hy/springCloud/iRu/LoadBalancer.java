package com.hy.springCloud.iRu;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    ServiceInstance service(List<ServiceInstance> serviceInstances);
}
