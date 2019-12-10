package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.domain.FootPrintItem;
import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.service.FootprintService;
import com.xmu.wowomall.footprint.util.ResponseUtil;
import com.xmu.wowomall.footprint.vo.FootPrintsItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wowoto
 * @date 12/09/2019
 */

@RestController
public class FootprintController {

    @Autowired
    private FootprintService footprintService;

    /**
     * 获取用户足迹信息
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @GetMapping("/footprints")
    public Object listUser(Integer userId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit
                       )
    {
        /* user有多个足迹，不会返回多个，只有返回1个 */
        /* user有1个足迹，只有返回0个 */
        if(userId==null)
        {
           return ResponseUtil.fail();
        }
         return footprintService.listFootprintsToUser(userId,page,limit);
    }


    /**
     *获取足迹信息/list
     */
    @GetMapping("/listAdmin")
    public Object listAdmin(
                    @RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit)
    {
        return footprintService.listFootprintsToAdmin(page,limit);
    }


    /**
     * 删除用户足迹/delete
     *
     * @param userId 用户ID
     * @param id  请求内容， { id: xxx }
     * @return 删除操作结果
     */
    @DeleteMapping("/footprints/{id}")
    public Object delete(Integer userId, @PathVariable("id") Integer id)
    {
       return footprintService.deleteFootprintOfUser(userId,id);
    }


    /**
     *内部接口:添加足迹信息/add
     *@param userId 用户ID
     *@param footPrintVo  足迹信息
     */
    @PostMapping("/footprints")
    public Object add(Integer userId, @RequestBody FootPrintsItemVo footPrintVo)
    {
        if(userId==null)
        {
            return ResponseUtil.fail();
        }
         FootPrintItem oneItem =footPrintVo.getFootPrintItem();
         return footprintService.insertFootprint(oneItem);
    }
}
