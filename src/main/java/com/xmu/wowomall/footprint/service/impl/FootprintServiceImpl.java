package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.util.ResponseCode;
import com.xmu.wowomall.footprint.util.ResponseUtil;
import com.xmu.wowomall.footprint.dao.FootprintDao;
import com.xmu.wowomall.footprint.domain.FootPrintItem;
import com.xmu.wowomall.footprint.service.FootprintService;
import com.xmu.wowomall.footprint.vo.FootPrintsItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private FootprintDao footprintDao;

    /**
     * 获取用户足迹信息
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public Object listFootprintsToUser(Integer userId,Integer page,Integer limit)
    {

        List<FootPrintItem> footPrintList=footprintDao.listFootPrintsToUser(userId,page,limit);
        List<FootPrintsItemVo> footPrintVoList=new ArrayList<>(footPrintList.size());
        for(FootPrintItem oneItem:footPrintList)
        {
            Integer goodsId=oneItem.getId();
            /**
             * 根据goodsId拿到item对应商品信息
             */
            FootPrintsItemVo oneVo=new FootPrintsItemVo();
            oneVo.setFootPrintItem(oneItem);;
            /**
             * 放入商品信息
             */
            footPrintVoList.add(oneVo);
        }
        return ResponseUtil.ok(footPrintVoList);

    }

    /**
     * 获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public Object listFootprintsToAdmin(Integer page,Integer limit)
    {
        List<FootPrintItem> footPrintList=footprintDao.listFootPrintsToAdmin(page,limit);
        List<FootPrintsItemVo> footPrintVoList=new ArrayList<>(footPrintList.size());
        for(FootPrintItem oneItem:footPrintList)
        {
            Integer goodsId=oneItem.getId();
            /**
             * 根据goodsId拿到item对应商品信息
             */
            FootPrintsItemVo oneVo=new FootPrintsItemVo();
            oneVo.setFootPrintItem(oneItem);;
            /**
             * 放入商品信息
             */
            footPrintVoList.add(oneVo);
        }
        return ResponseUtil.ok(footPrintVoList);
    }

    /**
     * 删除用户足迹/delete
     *
     * @param userId 用户ID
     * @param footprintId  请求内容， { id: xxx }
     * @return 删除操作结果
     */
    @Override
    public Object deleteFootprintOfUser(Integer userId,Integer footprintId)
    {
        FootPrintItem oneItem=footprintDao.findFootPrintById(footprintId);
        if(!oneItem.getId().equals(userId))
        {
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_INVALID_OPERATION.getCode(),ResponseCode.FOOTPRINT_INVALID_OPERATION.getMessage());
        }
        if(oneItem==null)
        {
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_UNKNOWN.getCode(),ResponseCode.FOOTPRINT_UNKNOWN.getMessage());
        }
        Integer result=footprintDao.deleteFootPrintById(footprintId);
        return ResponseUtil.ok(result);
    }

    /**
     * 新增一条足迹信息
     * @param oneItem
     * @return
     */
    @Override
    public Object insertFootprint(FootPrintItem oneItem)
    {
        Integer result=footprintDao.insertFootPrint(oneItem);
        return ResponseUtil.ok(result);
    }

}
