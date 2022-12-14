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
        ServiceInstance serviceInstance = loadBalancerClient.choose("SERVER-SERVICE");
        String url = serviceInstance.getUri()+"/server/host";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    public String getResultsFromServer(String searchTerm) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("SERVER-SERVICE");
        String url = serviceInstance.getUri()+"/server/search/"+ searchTerm;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    public String getResultsFromServerSort(String searchTerm,String sort) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("SERVER-SERVICE");
        String url = serviceInstance.getUri()+"/server/search/"+ searchTerm+"/"+sort;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
