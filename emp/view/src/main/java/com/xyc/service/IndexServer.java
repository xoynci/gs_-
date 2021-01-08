package com.xyc.service;

import com.alibaba.fastjson.JSONObject;
import com.xyc.pojo.Dept;
import com.xyc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServer {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    public String getServerUil() {
        ServiceInstance si = loadBalancerClient.choose("DEPTSERVER");
        String url="http://"+si.getHost()+":"+si.getPort();
        System.out.println(url);
        return url;
    }
    public List<Dept> getDepts(){
//        getServerUil();
        List<Dept> result = restTemplate.postForObject("http://DEPTSERVER/getdeptall",null,List.class);
//        System.out.println(String.format("%s %s %s",restTemplate.));
        return result;
    }
    public String insertUser(User user){
        String result = restTemplate.postForObject("http://USERSERVER/insertUser", JSONObject.toJSON(user), String.class);
        System.out.println("insert success");
        return result;
    }
}
