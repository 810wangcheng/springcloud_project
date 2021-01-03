package com.cy.controller;

import com.cy.client.UserClient;
import com.cy.pojo.User;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("consumer/user")
//@DefaultProperties(defaultFallback = "fallBackMethod")
public class UserController {

   /* @Autowired
    private RestTemplate restTemplate;*/

    /*@Autowired
    private DiscoveryClient discoveryClient;*/

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    @ResponseBody
    @HystrixCommand //指定这个方法需要熔断
    public String queryUserById(@PathVariable("id")long id){
        /*if (id == 1){
            throw new RuntimeException();  
        }*/
        /*List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);
        return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id,User.class);*/
        //return restTemplate.getForObject("http://service-provider/user/"+id,String.class);
        return this.userClient.findUserById(id).toString();
    }

    public String fallBackMethod(){
        return "服务正忙，请稍后重试";
    }
 }
