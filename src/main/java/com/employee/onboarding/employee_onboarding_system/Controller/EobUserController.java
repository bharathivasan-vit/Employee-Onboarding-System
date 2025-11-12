package com.employee.onboarding.employee_onboarding_system.Controller;

import com.employee.onboarding.employee_onboarding_system.Entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.Service.EobUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = {
        "http://localhost:5173/",
})
@RestController
@RequestMapping("/eobs/")
public class EobUserController {
    @Autowired
    EobUserService eobUserService;
    @PostMapping("sendOtp/{action}")
    public ResponseEntity<?> sendOtp(@RequestBody UserDetailsEntity user, @PathVariable String action) {
        String message = eobUserService.sendOtp(user,action);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", message,
                "email", user.getEmailId()
        ));
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDetailsEntity user, HttpServletRequest request){
        UserDetailsEntity data = eobUserService.register(user,request);
        return ResponseEntity.ok(Map.of(
                "status", 201,
                "data", data,
                "message", "Register Successfully"
        ));
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetailsEntity user,HttpServletRequest request){
        UserDetailsEntity data = eobUserService.login(user,request);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "data", data,
                "message", "Login Successfully"
        ));
    }
}
