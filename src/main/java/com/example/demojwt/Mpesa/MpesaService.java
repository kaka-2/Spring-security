package com.example.demojwt.Mpesa;

import com.example.demojwt.Applicationuser.service.HttpCall;
import com.example.demojwt.Utils.ApplicationUserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
public class MpesaService {

    public final static Logger logger = LoggerFactory.getLogger(MpesaService.class);

    @Autowired
    private HttpCall<MpesaAuthorizationResponse> mpesaAuthorizationResponseHttpCall;

    @Value("${mpesa.api.key}")
    private String consumer_key;

    @Value("${mpesa.api.secret}")
    private String consumer_secret;

    @Value("${mpesa.api.url}")
    private String app_url;

    public ApplicationUserResponse generateToken() {

        ApplicationUserResponse userResponse = new ApplicationUserResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String app_payload = consumer_key +":"+consumer_secret;

            byte[] auth = Base64.encodeBase64(app_payload.getBytes(Charset.forName("US-ASCII")));

            String authHeader = "Basic " + new String(auth);

            headers.add("authorization",authHeader);

            final ResponseEntity<MpesaAuthorizationResponse> responseEntity = mpesaAuthorizationResponseHttpCall.sendAPIGatewayGETRequest(app_url,headers,MpesaAuthorizationResponse.class);
            MpesaAuthorizationResponse authorizationResponse = responseEntity.getBody();

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println();
                logger.info("STATE 200 RESPONSE FROM MPESA GATEWAY: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authorizationResponse));
                System.out.println();

                userResponse.setStatusCode(HttpStatus.OK.value());
                userResponse.setData(authorizationResponse);
                userResponse.setMessage("SUCCESS");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userResponse;
    }

}
