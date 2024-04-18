package com.sr.externalApiIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalApiController {
	
	@Autowired
	private ExternalApiService externalApiService;

	@GetMapping("/externalApi")
	public ResponseEntity<ExternalResponsePojo> getResponse(){
		ExternalResponsePojo response = externalApiService.externalApi();
		return new ResponseEntity<>(response ,HttpStatus.OK);
	}
}
