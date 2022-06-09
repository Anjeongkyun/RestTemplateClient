package com.example.client.service;

import dto.UserRequest;
import dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;

@Service
public class RestTemplateService {

    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:5959")
                .path("/api/server/hello")
                .queryParam("name","AAA")
                .queryParam("age","55")
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse post() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:5959")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("60", "jeongkyun")
                .toUri();

        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http boddy json
        UserRequest req = new UserRequest();
        req.setName("jeongkyun");
        req.setAge(66);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> res = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println(res.getStatusCode());
        System.out.println(res.getHeaders());
        System.out.println(res.getBody());

        return res.getBody();
    }
}
