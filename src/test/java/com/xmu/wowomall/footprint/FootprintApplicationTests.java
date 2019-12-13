package com.xmu.wowomall.footprint;

import com.xmu.wowomall.footprint.controller.FootprintController;
import com.xmu.wowomall.footprint.domain.FootprintItem;

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




}
