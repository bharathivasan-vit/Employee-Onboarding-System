package com.employee.onboarding.employee_onboarding_system.controller;

import com.employee.onboarding.employee_onboarding_system.entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.EobService;
import com.employee.onboarding.employee_onboarding_system.service.EobUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eobs/auth/")
public class EobUserController {
    @Autowired EobUserService eobUserService;
    @Autowired EobService eobService;
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    private static final String EMAIL = "email";
    @PostMapping("sendOtp/{action}")
    public ResponseEntity<Map<String,Object>> sendOtp(@RequestBody UserDetailsEntity user, @PathVariable String action) {
        try{
            String message = eobUserService.sendOtp(user,action);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, message,
                    EMAIL, user.getEmailId()
            ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    STATUS, 404,
                    MESSAGE, e.getMessage()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @PostMapping("register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody UserDetailsEntity user, HttpServletRequest request){
        try{
            UserDetailsEntity data = eobUserService.register(user,request);
            return ResponseEntity.ok(Map.of(
                    STATUS, 201,
                    MESSAGE, "Register Successfully",
                    DATA, data
            ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    STATUS, 404,
                    MESSAGE, e.getMessage()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @PostMapping("login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDetailsEntity user,HttpServletRequest request){
        try{
            UserDetailsEntity data = eobUserService.login(user,request);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    DATA, data,
                    MESSAGE, "Login Successfully"
            ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    STATUS, 404,
                    MESSAGE, e.getMessage()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @GetMapping("getAllRoleList")
    public ResponseEntity<Map<String,Object>> getAllRoleList(){
        try{
            List<Map<String,Object>> result = eobService.getAllRoleList();
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Roles Fetched Successfully ",
                    DATA, result
            ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    STATUS, 404,
                    MESSAGE, e.getMessage()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
}
