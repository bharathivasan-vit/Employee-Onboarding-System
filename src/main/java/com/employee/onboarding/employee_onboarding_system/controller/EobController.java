package com.employee.onboarding.employee_onboarding_system.controller;

import com.employee.onboarding.employee_onboarding_system.config.AESUtil;
import com.employee.onboarding.employee_onboarding_system.entity.*;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.EobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eobs/client")
public class EobController {
    @Autowired EobService eobService;
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    @PostMapping("getUsersById")
    public ResponseEntity<Map<String, Object>> getUsersById(@RequestBody UserDetailsEntity inputUserId){
        try{
            int userId =inputUserId.getUserId();
            List<Map<String,Object>> result = eobService.getUsersById(userId);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "User Data Fetched Successfully",
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
    @PostMapping("getChecklistForUser")
    public ResponseEntity<Map<String, Object>> getChecklistForUser(@RequestBody UserDetailsEntity inputUserId) {
        try{
            int userId =inputUserId.getUserId();
            List<Map<String,Object>> result = eobService.getChecklistForUser(userId);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "User Data Fetched Successfully",
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
    @PutMapping("setChecklistStatus")
    public ResponseEntity<Map<String,Object>> setStatus(@RequestBody UserOnboardingStatusEntity inputStatus){
        try{
            int userId =inputStatus.getUserId();
            int checkListId =inputStatus.getCheckListId();
            boolean status =inputStatus.isStatus();
            String result = eobService.setStatus(userId,checkListId,status);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, result
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
    @PutMapping("setChecklistStatusTrue")
    public ResponseEntity<Map<String,Object>> setStatusTrue(@RequestBody UserOnboardingStatusEntity inputStatus) {
        try{
            int userId =inputStatus.getUserId();
            int checkListId =inputStatus.getCheckListId();
            String result = eobService.setStatus(userId,checkListId,true);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, result
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
