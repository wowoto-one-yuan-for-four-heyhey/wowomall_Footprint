package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.service.GoodsService;
import com.xmu.wowomall.footprint.service.RemoteGoodsService;
import com.xmu.wowomall.footprint.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    RemoteGoodsService remoteGoodsService;

    /**
     * 根据ID得到商品信息
     * @param id
     * @return
     */
    @Override
    public GoodsPo getGoodsById(Integer id){
        String res=remoteGoodsService.getGoodsById(id);
        return JacksonUtil.parseObject(res,"data",GoodsPo.class);
    }



}
