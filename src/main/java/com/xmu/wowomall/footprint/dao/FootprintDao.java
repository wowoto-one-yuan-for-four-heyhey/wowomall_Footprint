package com.xmu.wowomall.footprint.dao;

import com.xmu.wowomall.footprint.domain.FootPrintItem;
import com.xmu.wowomall.footprint.mapper.FootprintMapper;
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

    /**
     * 获取用户足迹信息
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public List<FootPrintItem> listFootPrintsToUser(Integer userId, Integer page, Integer limit)
    {
        Integer pageMapper=page-1;
        System.out.println("---------------------------");
        return footprintMapper.findFootPrintsByUserId(userId,pageMapper,limit);

    }

    /**
     * 管理员获取足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    public List<FootPrintItem> listFootPrintsToAdmin(Integer page, Integer limit)
    {
        return footprintMapper.findFootPrintsByUserId(-1,page,limit);
    }

    /**
     * 根据足迹ID返回足迹
     * @param footprintId
     * @return
     */
    public FootPrintItem findFootPrintById(Integer footprintId)
    {
        return footprintMapper.findFootPrintById(footprintId);

    }

    /**
     * 管理员删除用户足迹
     * @param footprintId
     * @return
     */
    public Integer deleteFootPrintById(Integer footprintId)
    {
        return footprintMapper.deleteFootPrintById(footprintId);
    }

    /**
     *
     * @param footprintItem
     * @return
     */
    public Integer insertFootPrint(FootPrintItem footprintItem)
    {
        return footprintMapper.insertFootPrint(footprintItem);
    }
}
