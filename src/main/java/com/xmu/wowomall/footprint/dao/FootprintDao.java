package com.xmu.wowomall.footprint.dao;

import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.Po.FootprintItemPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.mapper.FootprintMapper;
import com.xmu.wowomall.footprint.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/09/2019
 */
@Repository
public class FootprintDao {

    @Autowired
    private FootprintMapper footprintMapper;
    @Autowired
    private GoodsService goodsService;

    /**
     * 获取用户足迹信息
     * @param userId     userId为-1查询全部用户
     * @param goodsId   goodsId为-1查询全部商品
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public List<FootprintItem> listFootPrints(Integer userId, Integer goodsId,Integer page, Integer limit)
    {
        page=(page-1)*limit;
        List<FootprintItem> footprintList= footprintMapper.findFootprints(userId,goodsId,page ,limit);
        for(FootprintItem oneItem:footprintList) {
            Integer poId=oneItem.getGoodsId();
            GoodsPo goodsPo= goodsService.getGoodsById(poId);
            oneItem.setGoodsPo(goodsPo);
        }
        return footprintList;
    }



    /**
     * 根据足迹ID返回足迹
     * @param footprintId
     * @return
     */
    public FootprintItem findFootPrintById(Integer footprintId)
    {
        FootprintItem oneItem=footprintMapper.findFootprintById(footprintId);
        Integer poId=oneItem.getGoodsId();
        GoodsPo goodsPo= goodsService.getGoodsById(poId);
        oneItem.setGoodsPo(goodsPo);
        return oneItem;

    }

    /**
     * 管理员删除用户足迹
     * @param footprintId
     * @return
     */
    public Integer deleteFootPrintById(Integer footprintId)
    {
        return footprintMapper.deleteFootprintById(footprintId);
    }

    /**
     *插入一条足迹
     * @param footprintItem
     * @return
     */
    public Integer insertFootPrint(FootprintItem footprintItem)
    {
        return footprintMapper.insertFootprint(footprintItem);
    }

    /**
     * 根据添加的足迹信息查看数据库中有没有对应的
     * @param userId
     * @param goodsId
     * @return
     */
    public FootprintItem findFootprintByUserIdAndGoodsId(Integer userId,Integer goodsId)
    {
        FootprintItem item=footprintMapper.findFootprintByUserIdAndGoodsId(userId,goodsId);
        return item;
    }

    /**
     * 更新一条足迹
     * @param footprintItem
     * @return
     */
    public Integer updateFootprint(FootprintItem footprintItem)
    {
        return footprintMapper.updateFootprint(footprintItem);

    }
}
