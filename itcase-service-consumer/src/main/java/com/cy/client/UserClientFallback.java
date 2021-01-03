package com.cy.client;

import com.cy.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User findUserById(Long id) {
        User user = new User();
        user.setUsername("服务器正忙，请稍后重试！");
        return user;
    }
}
