package com.xmu.wowomall.footprint.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.xmu.wowomall.footprint.domain.po.FootprintItemPo;
import com.xmu.wowomall.footprint.service.RemoteGoodsService;
import com.xmu.wowomall.footprint.service.UserService;
import com.xmu.wowomall.footprint.dao.FootprintDao;
import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.service.FootprintService;
import com.xmu.wowomall.footprint.util.RunTimeUtil;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 生产者的组名
     */
    @Value("${rocketmq.producer.groupName}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    /**
     * 获取用户足迹信息
     *
     * @param userId
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public List<FootprintItem> listFootprintsToUser(Integer userId, Integer page, Integer limit) {
        List<FootprintItem> footprintItemList = footprintDao.listFootPrints(userId, -1, page, limit);
        return footprintItemList;

    }

    /**
     * 管理员获取用户足迹信息
     *
     * @param page
     * @param limit
     * @return 用户足迹列表
     */
    @Override
    public List<FootprintItem> listFootprintsToAdmin(Integer userId, Integer goodsId, Integer page, Integer limit) {
        List<FootprintItem> footprintList = footprintDao.listFootPrints(userId, goodsId, page, limit);
        return footprintList;
    }


    /**
     * 新增一条足迹信息
     *
     * @param footprintItemPo
     * @return
     */
    @Override
    public Integer insertFootprint(FootprintItemPo footprintItemPo) {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.setInstanceName(RunTimeUtil.getRocketMqUniqeInstanceName());
        // 找一下该记录是否存在
        FootprintItemPo oneItem = footprintDao.findFootprintByUserIdAndGoodsId(footprintItemPo.getUserId(), footprintItemPo.getGoodsId());
        //数据库中没有就插入
        if (oneItem == null) {
            try {
                producer.start();
                Object obj = JSONArray.toJSON(footprintItemPo);
                String json = obj.toString();
                Message message = new Message("Footprint", "Insert", json.getBytes());
                try {
                    producer.send(message);
                    return 1;
                } catch (Exception e) {

                    e.printStackTrace();
                    return null;
                }
            } catch (MQClientException e) {
                e.printStackTrace();
                return null;
            }
        }
        //有则更新
        else {
            try {
                producer.start();
                Object obj = JSONArray.toJSON(footprintItemPo);
                String json = obj.toString();
                Message message = new Message("Footprint", "Update", json.getBytes());
                try {
                    producer.send(message);
                    return 1;
                } catch (Exception e) {

                    e.printStackTrace();
                    return null;
                }
            } catch (MQClientException e) {

                e.printStackTrace();
                return null;
            }

        }

    }
}
