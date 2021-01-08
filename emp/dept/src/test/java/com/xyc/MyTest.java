package com.xyc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

//@SpringBootTest(classes = DeptStartApp.class)
public class MyTest {

    @Test
    public void  Mytest(){
        RestTemplate rs = new RestTemplate();
        String result = rs.getForObject("http://localhost:9090/getdeptall", String.class);
        System.out.println(result);
    }
}
