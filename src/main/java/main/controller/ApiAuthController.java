package main.controller;

import main.api.response.CheckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @GetMapping("/check")
    private ResponseEntity<CheckResponse> check() {
        return new ResponseEntity<>(new CheckResponse(), HttpStatus.OK);
    }
}
