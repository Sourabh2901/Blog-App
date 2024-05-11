package com.sr.Services.ServiceImpl;

import com.sr.Entities.ExternalApi;
import com.sr.Services.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceImpl implements ExternalApiService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.getCall_Url}")
    private String getCall_Url;

    @Value("${api.postCall_Url}")
    private String postCall_Url;

    @Override
    public ExternalApi externalGetCall() {
//        String URL = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<ExternalApi> response = restTemplate.getForEntity(getCall_Url, ExternalApi.class);
        return response.getBody();
    }

    @Override
    public ExternalApi externalPostCall(ExternalApi requestBody) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExternalApi> entity = new HttpEntity<>(requestBody ,headers);

        ResponseEntity<ExternalApi> postResponse = restTemplate.postForEntity(postCall_Url ,entity ,ExternalApi.class);
        return postResponse.getBody();
    }

}
