package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Morange on 2018/1/23.
 */
@RestController
public class Hello {
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("/hello")
    public String hello() throws InterruptedException {
        int i = new Random().nextInt(5000);
        Thread.sleep(i);
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info(localServiceInstance.getHost()+"-->"+localServiceInstance.getServiceId()+
        "-->"+i);
        return "hello";
    }

    @RequestMapping("/hello1")
    public String hello1(@RequestParam("name") String name,@RequestParam("age") String age){
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info(localServiceInstance.getHost()+"-->"+localServiceInstance.getServiceId());
        return name+"-->"+age;
    }
}
