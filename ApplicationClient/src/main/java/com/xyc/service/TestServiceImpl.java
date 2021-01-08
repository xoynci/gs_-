package com.xyc.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xyc.config.RestTemplatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getServerUil() {
        ServiceInstance si = loadBalancerClient.choose("APPLICATIONSERVER1");
        String url="http://"+si.getHost()+":"+si.getPort();
        System.out.println(url);
        return url;
    }

    @Override
    public String toHome() {
        String result = restTemplate.getForObject("http://APPLICATIONSERVER1/home", String.class);
        return result;
    }
}
