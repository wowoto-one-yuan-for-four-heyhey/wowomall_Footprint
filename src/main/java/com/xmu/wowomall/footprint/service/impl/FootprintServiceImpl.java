package com.xmu.wowomall.footprint.service.impl;

import com.xmu.wowomall.footprint.service.RemoteGoodsService;
import com.xmu.wowomall.footprint.service.UserService;
import com.xmu.wowomall.footprint.dao.FootprintDao;
import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * fringe
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
    private RemoteGoodsService remoteGoodsService;
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
        return footprintItemList;

    }

    /**
     * 管理员获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public List<FootprintItem> listFootprintsToAdmin(Integer userId,Integer goodsId,Integer page,Integer limit)
    {
        List<FootprintItem> footprintList=footprintDao.listFootPrints(userId,goodsId,page,limit);
        return footprintList;
    }

    /**
     * 删除用户足迹/delete
     *
     * @param userId 用户ID
     * @param footprintId  请求内容， { id: xxx }
     * @return 删除操作结果
     */
    /*
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
  */


    /**
     * 新增一条足迹信息
     * @param one
     * @return
     */
    @Override
    public FootprintItem  insertFootprint(FootprintItem one)
    {
        FootprintItem oneItem = footprintDao.findFootprintByUserIdAndGoodsId(one.getUserId(),one.getGoodsId());
        //数据库中没有就插入
        if(oneItem == null){
            footprintDao.insertFootprint(one);
        }
        //有则更新
        else{
            footprintDao.updateFootprint(one);
        }
        return one;
    }

}
