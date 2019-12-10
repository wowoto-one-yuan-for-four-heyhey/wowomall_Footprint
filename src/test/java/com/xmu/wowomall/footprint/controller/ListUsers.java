package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListUsers {

    @Value("http://${host}:${port}/footprints?userId=1")
    String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test1() throws Exception{
        /* 登陆 */

        URI uri = new URI(url);

        String response = testRestTemplate.getForObject(uri, String.class);

        Integer errno = JacksonUtil.parseInteger(response, "errno");
        assertEquals(0, errno);


    }


}
