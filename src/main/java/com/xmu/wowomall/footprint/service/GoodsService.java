package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("wowoto-goods")
public interface GoodsService {

    /**
     * 根据ID得到商品信息
     * @param id
     * @return
     */
    @GetMapping(value="goods/{id}")
    GoodsPo getGoodsById(@PathVariable("id") Integer id);


    /**
     * 根据商品名得到商品
     * @param goodsName
     * @return
     */
    @GetMapping(value="goods/innerSearch")
    GoodsPo getGoodsByName(String goodsName);
}
