package com.example.consolapp.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsoleCommunication {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String getHostInformation() {

        // get ServiceInstance list using serviceId
        ServiceInstance serviceInstance = loadBalancerClient.choose("SERVER-SERVICE");

        // endpoint // read URI and Add path that returns url
        String url = serviceInstance.getUri()+"/server/host";
        //RestTemplate create object for RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(url, String.class);

        return response;
    }
}
