package com.xmu.wowomall.footprint.mapper;

import com.xmu.wowomall.footprint.domain.FootPrintItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
     * 获取用户足迹信息 userId=-1，查询全部用户
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    List<FootPrintItem> findFootPrintsByUserId(Integer userId, Integer page, Integer limit);

    /**
     * 根据足迹ID返回足迹
     * @param footprintId
     * @return
     */
    FootPrintItem findFootPrintById(Integer footprintId);

    /**
     * 删除用户足迹
     * @param footprintId
     * @return
     */
    Integer deleteFootPrintById(Integer footprintId);

    /**
     * 添加足迹信息
     * @param oneItem
     * @return
     */
    Integer insertFootPrint(FootPrintItem oneItem);
}
