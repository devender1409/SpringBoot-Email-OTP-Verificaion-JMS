package com.devender.emailotpverification.controller;

import com.devender.emailotpverification.payload.RequestDto;
import com.devender.emailotpverification.payload.ResponseDto;
import com.devender.emailotpverification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/user-register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody RequestDto request) {
        ResponseDto res = this.userService.registerUser(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/user-verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp) {
        String res = this.userService.verifyUser(email, otp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
