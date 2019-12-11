package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.FootPrintItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Object listFootPrintsToUser(Integer userId,Integer page,Integer limit);

    /**
     * 获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public Object listFootPrintsToAdmin(Integer page,Integer limit);

    /**
     * 删除用户足迹/delete
     *
     * @param userId 用户ID
     * @param footprintId  请求内容， { id: xxx }
     * @return 删除操作结果
     */
    public Object deleteFootprintOfUser(Integer userId,Integer footprintId);

    /**
     * 新增一条足迹信息
     * @param oneItem
     * @return
     */
    public Object insertFootprint(FootPrintItem oneItem);
}
