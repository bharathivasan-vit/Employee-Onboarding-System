package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.entity.RoleListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.admin.AdminRoleService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            return ResponseEntity.ok(createResponse(200,"Role Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/getRoleById")
    public ResponseEntity<Map<String,Object>> getRoleById(@RequestBody RoleListEntity inputRoleId) {
        try{
            int roleId = inputRoleId.getRoleId();
            List<Map<String, Object>> result = adminRoleService.getRoleById(roleId);
            return ResponseEntity.ok(createResponse(200,"Role Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/getRoleByName")
    public ResponseEntity<Map<String,Object>> getRoleByName(@RequestBody RoleListEntity inputRoleName) {
        try{
            String roleName = inputRoleName.getRoleName();
            List<Map<String, Object>> result = adminRoleService.getRoleByName(roleName);
            return ResponseEntity.ok(createResponse(200,"Role Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/createRole")
    public ResponseEntity<Map<String,Object>> createRole(@RequestBody RoleListEntity inputRole,HttpServletRequest request) {
        try{
            RoleListEntity result = adminRoleService.createRole(inputRole,request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createResponse(201,"Role Created Successfully",result));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(500,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PutMapping("/updateRole")
    public ResponseEntity<Map<String, Object>> updateRole(@RequestBody RoleListEntity inputRole, HttpServletRequest request) {
        try{
            int roleId = inputRole.getRoleId();
            RoleListEntity result = adminRoleService.updateRole(roleId, inputRole, request);
            return ResponseEntity.ok(createResponse(201,"Role Updated Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404, e.getMessage(),null));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(500,e.getMessage(),null));
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
