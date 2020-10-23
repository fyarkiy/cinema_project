package com.cinema.controllers;

import com.cinema.model.dto.UserRequestDto;
import com.cinema.security.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinema")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                                userRequestDto.getPassword());
    }
}
