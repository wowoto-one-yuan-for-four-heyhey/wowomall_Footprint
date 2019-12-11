package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.domain.FootPrintItem;
import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.service.FootprintService;
import com.xmu.wowomall.footprint.util.ResponseCode;
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
    @GetMapping("footprints")
    public Object listUser(Integer userId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit
                       )
    {
        if(userId==null) {
           return ResponseUtil.fail();
        }
         return footprintService.listFootPrintsToUser(userId,page,limit);
    }


    /**
     *获取足迹信息/list
     */
    @GetMapping("footprints/listAdmin")
    public Object listAdmin(
                    @RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit)
    {
        return footprintService.listFootPrintsToAdmin(page,limit);
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
        /* 当 userId 不等于 footPrintVo 中的userId的时候也可以插入 ？*/
        /* 当相同用户相同商品的浏览足迹是覆盖还是两个同时保留 ？  插入最新的 */
        /* 当用户的goodsId为空的时候也可以插入 ？ */
        if(userId==null)
        {
            return ResponseUtil.fail();
        }
        if(!userId.equals(footPrintVo.getFootPrintItem().getUserId())){
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_INVALID_OPERATION.getCode(),ResponseCode.FOOTPRINT_INVALID_OPERATION.getMessage());
        }
         FootPrintItem oneItem = footPrintVo.getFootPrintItem();
        if(oneItem.getGoodsId()==null){
            return ResponseUtil.fail(ResponseCode.FOOTPRINT_INVALID_OPERATION.getCode(),ResponseCode.FOOTPRINT_INVALID_OPERATION.getMessage());
        }
         return footprintService.insertFootprint(oneItem);
    }
}
