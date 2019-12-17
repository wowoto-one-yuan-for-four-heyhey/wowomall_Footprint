package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.domain.User;
import com.xmu.wowomall.footprint.service.RemoteUserService;
import com.xmu.wowomall.footprint.service.UserService;
import com.xmu.wowomall.footprint.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fringe
 * @date 12/16/2019
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RemoteUserService remoteUserService;
    /**
     * 根据ID拿到user
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId){
        String json =remoteUserService.getUserById(userId);
        User user= JacksonUtil.parseObject(json,"data",User.class);
        return user;
    }
}
