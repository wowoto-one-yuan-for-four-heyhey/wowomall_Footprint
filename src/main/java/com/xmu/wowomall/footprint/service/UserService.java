package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("UserInfo")
public interface UserService {
    /**
     * 根据用户名得到用户信息
     * @param username
     * @return
     */
    @GetMapping(value="user/username")
    User getUserByName(@RequestParam String username);

    /**
     * test
     * @return
     */
    @GetMapping(value="hello")
    String test();
}
