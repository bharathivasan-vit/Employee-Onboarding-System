package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.entity.RoleListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.admin.AdminRoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eobs/admin/role")
public class AdminRoleController {
    @Autowired AdminRoleService adminRoleService;
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    @GetMapping("/getAllRole")
    public ResponseEntity<Map<String,Object>> getAllRole() {
        try{
            List<Map<String, Object>> result = adminRoleService.getAllRole();
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Role Fetched Successfully",
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
    @PostMapping("/getRoleById")
    public ResponseEntity<Map<String,Object>> getRoleById(@RequestBody RoleListEntity inputRoleId) {
        try{
            int roleId = inputRoleId.getRoleId();
            List<Map<String, Object>> result = adminRoleService.getRoleById(roleId);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Role Fetched Successfully",
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
    @PostMapping("/createRole")
    public ResponseEntity<Map<String,Object>> createRole(@RequestBody RoleListEntity inputRole,HttpServletRequest request) {
        try{
            RoleListEntity result = adminRoleService.createRole(inputRole,request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    STATUS, 201,
                    MESSAGE, "Role Created Successfully",
                    DATA, result
            ));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @PutMapping("/updateRole")
    public ResponseEntity<Map<String, Object>> updateRole(@RequestBody RoleListEntity inputRole, HttpServletRequest request) {
        try{
            int roleId = inputRole.getRoleId();
            RoleListEntity result = adminRoleService.updateRole(roleId, inputRole, request);
            return ResponseEntity.ok(Map.of(
                    STATUS, 201,
                    DATA, result,
                    MESSAGE, "Role Updated Successfully"
            ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    STATUS, 404,
                    MESSAGE, e.getMessage()
            ));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    STATUS, 500,
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
