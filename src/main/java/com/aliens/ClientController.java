package com.aliens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("hi")
    public String sayHi(){

        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-client-two");
        String collect = instances.stream().map(i -> i.getUri().toString()).collect(Collectors.joining());
        return collect;
    }

}
