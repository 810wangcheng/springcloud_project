package com.cy.client;

import com.cy.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-provider",fallback = UserClientFallback.class) //声明一个接口是feign接口，指定服务id
public interface UserClient {

    @GetMapping("user/{id}") //路径在方法上添加
    User findUserById(@PathVariable("id") Long id);
}
