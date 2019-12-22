package com.xmu.wowomall.footprint.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmu.wowomall.footprint.dao.FootprintDao;
import com.xmu.wowomall.footprint.domain.FootprintItem;
import com.xmu.wowomall.footprint.domain.po.FootprintItemPo;
import com.xmu.wowomall.footprint.mapper.FootprintMapper;
import com.xmu.wowomall.footprint.util.JacksonUtil;
import com.xmu.wowomall.footprint.util.RunTimeUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author fringe
 */
@Component
public class FootprintConsumer implements CommandLineRunner {

    /**
     * 生产者的组名
     */

    @Value("${rocketmq.consumer.groupName}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;


    @Autowired
    FootprintDao footprintDao;



    @PostConstruct
    public void footprintConsumer() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(producerGroup);
            consumer.setNamesrvAddr(namesrvAddr);
            // 订阅FootprintTopic的所有tag，从头开始读取
            consumer.subscribe("Footprint", "*");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setInstanceName(RunTimeUtil.getRocketMqUniqeInstanceName());
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {

                    for (MessageExt messageExt:msgs){
                        String messageBody = new String(messageExt.getBody());
                        System.out.println(messageBody);
                        FootprintItem oneItem = JacksonUtil.string2Obj(messageBody, FootprintItem.class);
                        System.out.println("one" +"item.user:" + oneItem.getUserId());
                        if("Insert".equals(messageExt.getTags()))
                        {
                            footprintDao.insertFootprint(oneItem);
                            System.out.println("插入一条消息"+"one" +"item.user:" + oneItem.getUserId());
                        }

                        if("Update".equals(messageExt.getTags()))
                        {
                            FootprintItem fp=footprintDao.findFootprintByUserIdAndGoodsId(oneItem.getUserId(),oneItem.getGoodsId());
                            System.out.println(fp.getUserId());
                            footprintDao.updateFootprint(fp);
                            System.out.println("更新一条消息"+"one" +"item.user:" + oneItem.getUserId());
                        }

                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        footprintConsumer();
    }

}
