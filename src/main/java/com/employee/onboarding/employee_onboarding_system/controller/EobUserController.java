package com.employee.onboarding.employee_onboarding_system.controller;

import com.employee.onboarding.employee_onboarding_system.entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.EobService;
import com.employee.onboarding.employee_onboarding_system.service.EobUserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            return ResponseEntity.ok(createResponse(200,message,user.getEmailId()));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody UserDetailsEntity user, HttpServletRequest request){
        try{
            UserDetailsEntity data = eobUserService.register(user,request);
            return ResponseEntity.ok(createResponse(201,"Register Successfully",data));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDetailsEntity user,HttpServletRequest request){
        try{
            UserDetailsEntity data = eobUserService.login(user,request);
            return ResponseEntity.ok(createResponse(200,"Login Successfully",data));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @GetMapping("getAllRoleList")
    public ResponseEntity<Map<String,Object>> getAllRoleList(){
        try{
            List<Map<String,Object>> result = eobService.getAllRoleList();
            return ResponseEntity.ok(createResponse(200,"Roles Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }

    private Map<String, Object> createResponse(int status, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, status);
        map.put(MESSAGE, message);
        if (data != null) {
            map.put(DATA, data);
        }
        return map;
    }
}
