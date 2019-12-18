package com.xmu.wowomall.footprint.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: fringe
 * @Description:
 * @Date: 2019/12/16 20:08
 */
@Service
@FeignClient("userInfoService")
public interface RemoteUserService {

    /**
     * 根据ID拿到user
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
    String getUserById(@PathVariable Integer id);
}
