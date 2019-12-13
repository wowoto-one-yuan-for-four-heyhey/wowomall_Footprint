package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.Goods;
import com.xmu.wowomall.footprint.domain.Po.FootprintItemPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.domain.User;
import com.xmu.wowomall.footprint.service.FootprintService;
import com.xmu.wowomall.footprint.service.GoodsService;
import com.xmu.wowomall.footprint.service.UserService;
import com.xmu.wowomall.footprint.util.ResponseCode;
import com.xmu.wowomall.footprint.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/09/2019
 */


@RestController
public class FootprintController {

    @Autowired
    private FootprintService footprintService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @GetMapping("footprints")
    public Object listUser(
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        Integer userId =Integer.valueOf(request.getHeader("id"));
        List<FootprintItem> list = footprintService.listFootprintsToUser(userId,page,limit);
        return ResponseUtil.ok(list);
    }


    /**
     *获取足迹信息/list
     */
    @GetMapping("admin/footprints")
    public Object listAdmin(
            @RequestParam(defaultValue = "")  String userName,
            @RequestParam(defaultValue = "")  String goodsName,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit){
        User user= userService.getUserByName(userName);
        if(user==null){
            return ResponseUtil.fail();
        }
        GoodsPo goodsPo= goodsService.getGoodsByName(goodsName);
        if(goodsPo==null){
            return ResponseUtil.fail();
        }
        if(userName.equals("")){
            user.setId(-1);
        }
        if(goodsName.equals("")){
            goodsPo.setId(-1);
        }
        List<FootprintItem> list = footprintService.listFootprintsToAdmin(user,goodsPo,page,limit);
        return list;
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
     *内部接口:添加足迹信息
     *@param footprintItem  足迹信息
     */
    @PostMapping("/footprints")
    public Object add( FootprintItem footprintItem)
    {
         Integer userId =Integer.valueOf(request.getHeader("id"));
         if(userId==null){
             return ResponseUtil.fail();
         }
        String key;
        HashMap<String,Integer> result = footprintService.insertFootprint(footprintItem);
        key="id";
        if(result.containsKey(key)){
            footprintItem.setId(result.get("id"));
        }
        else {
            key="update";
            if(result.containsKey(key)){
                return ResponseUtil.ok(footprintItem);
            }
            else{
                return ResponseUtil.fail();
            }
       }
        return ResponseUtil.ok(footprintItem);
    }
}
