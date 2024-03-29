package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: fringe
 * @Description:
 * @Date: 2019/12/16 20:08
 */
@Service

public interface UserService {

    /**
     * 根据ID拿到user
     * @param userId
     * @return
     */
    User getUserById(Integer userId);


}
