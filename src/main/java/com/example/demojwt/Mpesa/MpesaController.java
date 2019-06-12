package com.example.demojwt.Mpesa;

import com.example.demojwt.Utils.ApplicationUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpesaController {

    @Autowired
    private MpesaService mpesaService;

    public static final Logger logger = LoggerFactory.getLogger(MpesaController.class);

    @PostMapping("/auth")
    public ResponseEntity<ApplicationUserResponse> auth() {
        ApplicationUserResponse applicationUserResponse = mpesaService.generateToken();

        return new ResponseEntity<>(applicationUserResponse, HttpStatus.OK);
    }

}
