package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.Log;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.domain.User;
import com.xmu.wowomall.footprint.service.*;
import com.xmu.wowomall.footprint.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private LogService logService;
    @Autowired
    private RemoteLogService remoteLogService;



    /**
     * 获取用户足迹信息
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @GetMapping("footprints")
    public Object listUser(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        Integer userId =Integer.valueOf(request.getHeader("id"));
        if(userId==null){
            return ResponseUtil.unlogin();
        }
        if(userId<=0){
            return ResponseUtil.badArgumentValue();
        }
        List<FootprintItem> list = footprintService.listFootprintsToUser(userId,page,limit);
        return ResponseUtil.ok(list);
    }


    /**
     *管理员获取足迹信息/list
     */
    @GetMapping("admin/footprints")
    public Object listAdmin(HttpServletRequest httpServletRequest,
            @RequestParam(defaultValue = "-1")  Integer userId,
            @RequestParam(defaultValue = "-1")  Integer goodsId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit)
    {
        Integer adminId =Integer.valueOf(request.getHeader("id"));
        if(adminId==null || adminId <= 0){
             return ResponseUtil.unlogin();
        }
        if(userId>0) {
            User user = userService.getUserById(userId);
            if(user==null){
                return ResponseUtil.badArgumentValue();
            }
        }
        if(goodsId>0) {
            GoodsPo goodsPo= goodsService.getGoodsById(goodsId);
            if(goodsPo==null){
                return ResponseUtil.badArgumentValue();
            }
        }
        List<FootprintItem> list = footprintService.listFootprintsToAdmin(userId,goodsId,page,limit);

        Log log=new Log();
        log.setType(0);
        log.setStatusCode(1);
        log.setActions("查询足迹");
        log.setActionId(1);
        remoteLogService.addLog(log);
        return ResponseUtil.ok(list);
    }



    /**
     *内部接口:添加足迹信息
     *@param footprintItem  足迹信息
     */
    @PostMapping("/footprints")
    public Object addFootprint(@RequestBody FootprintItem footprintItem)
    {
         if(footprintItem==null){
             return ResponseUtil.badArgumentValue();
         }
         if(footprintItem.getUserId()==null){
             return ResponseUtil.badArgumentValue();
         }
         if(footprintItem.getGoodsId()==null){
             return ResponseUtil.badArgumentValue();
         }
        FootprintItem oneItem = footprintService.insertFootprint(footprintItem);
        return ResponseUtil.ok(oneItem);
    }
}
