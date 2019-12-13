package com.xmu.wowomall.footprint.mapper;

import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.Po.FootprintItemPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/09/2019
 */
@Repository
@Mapper

public interface FootprintMapper {

    /**
     * 获取用户足迹信息 userId=-1，查询全部用户 userName为空，查询全部用户
     * @param userId
     * @param goodsId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    List<FootprintItem> findFootprints(Integer userId,Integer goodsId, Integer page, Integer limit);

    /**
     * 根据足迹ID返回足迹
     * @param footprintId
     * @return
     */
    FootprintItem findFootprintById(Integer footprintId);

    /**
     * 删除用户足迹
     * @param footprintId
     * @return
     */
    Integer deleteFootprintById(Integer footprintId);

    /**
     * 添加足迹信息
     * @param one
     * @return
     */
    Integer insertFootprint(FootprintItem one);

    /**
     * 根据添加的信息查询是否该足迹在数据库中
     * @param userId
     * @param goodsId
     * @return
     */
    FootprintItem findFootprintByUserIdAndGoodsId(Integer userId, Integer goodsId);

    /**
     * 更新一条足迹
     * @param footprintItem
     * @return
     */
    public Integer updateFootprint(FootprintItem footprintItem);
}
