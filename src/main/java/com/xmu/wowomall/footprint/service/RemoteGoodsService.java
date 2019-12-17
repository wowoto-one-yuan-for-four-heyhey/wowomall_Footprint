package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("goodsInfoService")
public interface RemoteGoodsService {

    /**
     * 根据ID得到商品信息
     * @param id
     * @return
     */
    @GetMapping(value="goods/{id}")
    String getGoodsById(@PathVariable("id") Integer id);



}
