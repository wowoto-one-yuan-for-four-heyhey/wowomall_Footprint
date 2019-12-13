package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.Po.FootprintItemPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.domain.User;
import com.xmu.wowomall.footprint.service.GoodsService;
import com.xmu.wowomall.footprint.service.UserService;
import com.xmu.wowomall.footprint.util.ResponseCode;
import com.xmu.wowomall.footprint.util.ResponseUtil;
import com.xmu.wowomall.footprint.dao.FootprintDao;
import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private FootprintDao footprintDao;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 获取用户足迹信息
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public List<FootprintItem> listFootprintsToUser(Integer userId, Integer page, Integer limit)
    {
        List<FootprintItem> footprintItemList=footprintDao.listFootPrints(userId,-1,page,limit);
        for(FootprintItem oneItem:footprintItemList) {
            Integer goodsId=oneItem.getGoodsId();
            GoodsPo goodsPo=goodsService.getGoodsById(goodsId);
            oneItem.setGoodsPo(goodsPo);
        }
        return footprintItemList;

    }

    /**
     * 管理员获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public List<FootprintItem> listFootprintsToAdmin(User user, GoodsPo goodsPo,Integer page,Integer limit)
    {

        Integer userId=user.getId();
        Integer poId=goodsPo.getId();
        List<FootprintItem> footprintList=footprintDao.listFootPrints(userId,poId,page,limit);
        for(FootprintItem oneItem:footprintList) {
            Integer goodsId=oneItem.getGoodsId();
            GoodsPo onePo=goodsService.getGoodsById(goodsId);
            oneItem.setGoodsPo(onePo);
        }
        return footprintList;
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
        FootprintItem oneItem=footprintDao.findFootPrintById(footprintId);
        if(oneItem==null) {
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_UNKNOWN.getCode(),ResponseCode.FOOTPRINT_UNKNOWN.getMessage());
        }
        if(!oneItem.getUserId().equals(userId)) {
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_INVALID_OPERATION.getCode(),ResponseCode.FOOTPRINT_INVALID_OPERATION.getMessage());
        }

        Integer result=footprintDao.deleteFootPrintById(footprintId);
        return ResponseUtil.ok(result);
    }

    /**
     * 新增一条足迹信息
     * @param one
     * @return
     */
    @Override
    public HashMap<String,Integer> insertFootprint(FootprintItem one)
    {
        FootprintItem oneItem=footprintDao.findFootprintByUserIdAndGoodsId(one.getUserId(),one.getGoodsId());
        HashMap<String,Integer> result= new HashMap<>();
        //数据库中没有就插入
        if(oneItem==null){
            Integer itemId=footprintDao.insertFootPrint(one);
            result.put("id",itemId);
        }
        //有则更新
        else{
            Integer updateResult=footprintDao.updateFootprint(one);
            if(updateResult.equals(1)){
                result.put("update",1);
            }
            else {
                result.put("update",-1);
            }
        }
        return result;
    }

}
