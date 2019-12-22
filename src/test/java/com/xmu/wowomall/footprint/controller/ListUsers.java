package com.xmu.wowomall.footprint.controller;

import com.xmu.wowomall.footprint.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
public class ListUsers {

    @Value("http://${host}:${port}/footprints")
    String url;

    @Autowired
    private TestRestTemplate testRestTemplate;


    private HttpHeaders getHttpHeaders(URI uri) throws URISyntaxException {
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        if (!adminAccount.addToken(httpHeaders)) {
            //登录失败
            assertTrue(false);
        }
        return httpHeaders;
    }

    @Test
    public void test1() throws Exception{
        /* 登陆 */
/*
        URI uri = new URI(url);

        String response = testRestTemplate.getForObject(uri, String.class);

        Integer errno = JacksonUtil.parseInteger(response, "errno");
        assertEquals(0, errno);


    }


}
*/