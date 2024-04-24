package com.devender.emailotpverification.service;

import com.devender.emailotpverification.entity.User;
import com.devender.emailotpverification.payload.RequestDto;
import com.devender.emailotpverification.payload.ResponseDto;
import com.devender.emailotpverification.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final EmailService emailService;


    public ResponseDto registerUser(RequestDto request){
        ResponseDto res = new ResponseDto();

        User existingUser = this.userRepo.findByEmail(request.getEmail());
        if(existingUser != null) {
            res.setMessage("User already registered.");
        } else {
            Random r = new Random();
            String otp = String.format("%06d", r.nextInt(100000));

            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setOtp(otp);
            newUser.setVerified(false);

            User savedUser = this.userRepo.save(newUser);
            String subject = "Email Verfication";
            String body = "Your verification OTP is "+savedUser.getOtp();
            //Email Send
            this.emailService.sendEmail(savedUser.getEmail(), subject, body);

            res.setUserId(savedUser.getUserId());
            res.setUsername(savedUser.getUsername());
            res.setEmail(savedUser.getEmail());
            res.setMessage("OTP sent successfully!");

        }

        return res;

    }


    public String verifyUser(String email, String otp) {
        String response = "";
        User user = this.userRepo.findByEmail(email);

        if(user != null && user.isVerified()) {
            response = "User is already verified.";
        }else if(otp.equals(user.getOtp())) {
            user.setVerified(true);
            this.userRepo.save(user);
            response = "User verified successfully.";
        }else {
            response = "User not verified.";
        }

        return response;

    }


}
