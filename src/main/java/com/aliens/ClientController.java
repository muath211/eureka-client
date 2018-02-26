package com.aliens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WordService wordService;

    @GetMapping("/hi")
    @ResponseBody
    public String sayHi() {

        List<ServiceInstance> instances = discoveryClient.getInstances("hala-feek");
        String collect = instances.stream().map(i -> i.getUri().toString()).collect(Collectors.joining());

        String services = discoveryClient.getServices().stream().collect(Collectors.joining());


        return collect + "\n services: \n" + services;
    }

    @GetMapping("/load/hello")
    public @ResponseBody
    String sayLoadBalancedHello() {

        String forObject = restTemplate.getForObject("http://TEST/hello", String.class);
        return forObject;
    }

    @GetMapping("/hello")
    public @ResponseBody
    String sayHello() {
        return wordService.getWord();
    }

}
