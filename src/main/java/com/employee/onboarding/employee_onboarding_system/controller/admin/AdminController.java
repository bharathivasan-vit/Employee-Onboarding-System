package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eobs/admin/")
public class AdminController {
    @Autowired AdminService adminService;
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    @GetMapping("getOnboardingBulkRecords")
    public ResponseEntity<Map<String,Object>> getOnboardingBulkRecords(){
       try{
           List<Map<String,Object>> result =  adminService.getOnboardingBulkRecords();
           if(result.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,"Onboarding data not found",null));
           }
           return ResponseEntity.ok(createResponse(200,"Onboarding Data Fetched Successfully",result));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
       }
    }
    @GetMapping("getOnboardingBulkRecordsClearView")
    public ResponseEntity<Map<String,Object>> getOnboardingBulkRecordsClearView(){
        try {
            List<Map<String,Object>> result =  adminService.getOnboardingBulkRecordsClearView();
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,"Onboarding data not found",null));
            }
            return ResponseEntity.ok(createResponse(200,"Onboarding Data Fetched Successfully",result));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @GetMapping("getAllUsersByVerified")
    public ResponseEntity<Map<String, Object>> getAllUsersByVerified(){
        try{
            List<Map<String,Object>> result = adminService.getAllUsersByVerified();
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,"User not found",null));
            }
            return ResponseEntity.ok(createResponse(200,"User Fetched Successfully",result));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("getUsersByName")
    public ResponseEntity<Map<String, Object>> getUsersByName(@RequestBody UserDetailsEntity inputUserName){
        try{
            String userName = inputUserName.getUserName();
            List<Map<String,Object>> result = adminService.getUsersByName(userName);
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,"User not found",null));
            }
            return ResponseEntity.ok(createResponse(200,"User Fetched Successfully",result));
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
