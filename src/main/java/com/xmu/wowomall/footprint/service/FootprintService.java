package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.Po.FootprintItemPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public interface FootprintService {

    /**
     * 获取用户足迹信息
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public List<FootprintItem> listFootprintsToUser(Integer userId, Integer page, Integer limit);

    /**
     * 获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public List<FootprintItem> listFootprintsToAdmin(Integer userId,Integer goodsId, Integer page, Integer limit);



    /**
     * 新增一条足迹信息
     * @param one
     * @return
     */
    FootprintItem  insertFootprint(FootprintItem one);
}
