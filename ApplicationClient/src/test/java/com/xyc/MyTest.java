package com.xyc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = StartUpClient.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {

    @Test
    public void getTest(){
        RestTemplate restTemplate=new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8001/home", String.class);
        System.out.println(result);
    }
}
