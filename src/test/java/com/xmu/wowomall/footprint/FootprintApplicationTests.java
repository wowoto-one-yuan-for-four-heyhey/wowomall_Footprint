package com.xmu.wowomall.footprint;

import com.xmu.wowomall.footprint.controller.FootprintController;
import com.xmu.wowomall.footprint.domain.FootPrintItem;
import com.xmu.wowomall.footprint.vo.FootPrintsItemVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDateTime;

@SpringBootTest
class FootprintApplicationTests {

    @Autowired
    FootprintController footprintController;


    @Test
    void contextLoads() throws Exception {
        Integer userId = 10;
        FootPrintsItemVo footPrintsItemVo = new FootPrintsItemVo();
        FootPrintItem footPrintItem = new FootPrintItem();
        footPrintItem.setBirthTime(LocalDateTime.now());
        footPrintItem.setGmtCreate(LocalDateTime.now());
        footPrintItem.setId(10);
        footPrintItem.setUserId(2);
        footPrintItem.setGoodsId(10);
        footPrintsItemVo.setFootPrintItem(footPrintItem);
        footprintController.add(userId,footPrintsItemVo);

    }

}
