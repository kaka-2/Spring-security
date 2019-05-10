package com.example.demojwt.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper();
        wrapper.setMessage(e.getMessage());
        wrapper.setTimestamp(Calendar.getInstance().getTimeInMillis());
        wrapper.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        httpServletResponse.getOutputStream().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wrapper));
    }
}
