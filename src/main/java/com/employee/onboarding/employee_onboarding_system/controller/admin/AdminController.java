package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                       STATUS, 404,
                       MESSAGE,"Onboarding data not found"
               ));
           }
           return ResponseEntity.ok(Map.of(
                   STATUS, 200,
                   MESSAGE, "Onboarding Data Fetched Successfully",
                   DATA, result
           ));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                   STATUS, 500,
                   MESSAGE, e.getMessage()
           ));
       }
    }
    @GetMapping("getOnboardingBulkRecordsClearView")
    public ResponseEntity<Map<String,Object>> getOnboardingBulkRecordsClearView(){
        try {
            List<Map<String,Object>> result =  adminService.getOnboardingBulkRecordsClearView();
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        STATUS, 404,
                        MESSAGE,"Onboarding data not found"
                ));
            }
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Onboarding Data Fetched Successfully",
                    DATA, result
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @PostMapping("getOnboardingBulkRecordsByFilter")
    public ResponseEntity<Map<String,Object>> getOnboardingBulkRecordsByFilter(@RequestBody Map<String, Object> filterData){
        try{
            LocalDate startDate = filterData.get("startDate") != null ? LocalDate.parse(filterData.get("startDate").toString()) : null;
            LocalDate endDate = filterData.get("endDate") != null ? LocalDate.parse(filterData.get("endDate").toString()) : null;
            Boolean status = filterData.get("status") != null ? Boolean.valueOf(filterData.get("status").toString()) : null;
            Integer roleId = filterData.get("roleId") != null ? Integer.valueOf(filterData.get("roleId").toString()) : null;
            String emailId = (String) filterData.get("emailId");

            List<Map<String,Object>> result = adminService.getOnboardingBulkRecordsByFilter(startDate, endDate, status, roleId, emailId);
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        STATUS, 404,
                        MESSAGE,"Onboarding data not found"
                ));
            }
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Onboarding Data Fetched Successfully",
                    DATA, result
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @GetMapping("getAllUsersByVerified")
    public ResponseEntity<Map<String, Object>> getAllUsersByVerified(){
        try{
            List<Map<String,Object>> result = adminService.getAllUsersByVerified();
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        STATUS, 404,
                        MESSAGE,"User not found"
                ));
            }
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "User Fetched Successfully",
                    DATA, result
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
}
