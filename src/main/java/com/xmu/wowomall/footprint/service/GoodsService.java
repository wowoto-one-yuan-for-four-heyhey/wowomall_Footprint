package com.xmu.wowomall.footprint.service;

import com.netflix.discovery.converters.Auto;
import com.xmu.wowomall.footprint.domain.po.GoodsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lenovo
 */
@Service
public interface GoodsService {
    /**
     * 根据ID得到商品信息
     * @param id
     * @return
     */
    GoodsPo getGoodsById(Integer id);



}
