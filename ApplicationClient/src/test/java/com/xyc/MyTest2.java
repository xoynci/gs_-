package com.xyc;

import com.xyc.config.RestTemplatConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

//@SpringBootTest(classes = StartUpClient.class)
public class MyTest2 {

    @Test
    public void mytest2(){

        RestTemplate restTemplate=new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:9001/toHome", String.class);
        System.out.println(result);

    }
}
