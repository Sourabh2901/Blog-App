package com.sr.externalApiIntegration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sr.config.AppCache;


@Component
public class ExternalApiService {
	
//    @Autowired
//    private RestTemplate restTemplate;
	
	@Autowired
	private AppCache appCache;
    
	
	@Value("${api.url}")
	private String api;

	public ExternalResponsePojo externalApi() {
		
	
//		String api = appCache.cache.get("externalApi");
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder()
									.uri(URI.create(api))
									.GET()
									.build();
		
		HttpResponse<String> response;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			ExternalResponsePojo obj = objectMapper.readValue(response.body(), ExternalResponsePojo.class);
			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
