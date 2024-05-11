package com.sr.Controller;

import com.sr.Entities.ExternalApi;
import com.sr.Paylods.Dtos.UserDto;
import com.sr.Services.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    ExternalApiService externalApiService;

    @GetMapping("/externalCall")
    private ResponseEntity<?> externalGetCall() {
        ExternalApi response = externalApiService.externalGetCall();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/externalCall")
    private ResponseEntity<?> externalPostCall(@RequestBody ExternalApi requestBody) {
        ExternalApi response = externalApiService.externalPostCall(requestBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
