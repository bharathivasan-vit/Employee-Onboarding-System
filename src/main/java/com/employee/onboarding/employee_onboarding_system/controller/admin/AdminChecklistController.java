package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.entity.CheckListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.admin.AdminChecklistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eobs/admin/checklist")
public class AdminChecklistController {
    @Autowired AdminChecklistService adminChecklistService;
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    @GetMapping("/getAllChecklist")
    public ResponseEntity<Map<String,Object>> getAllChecklist() {
        try{
            List<Map<String, Object>> result = adminChecklistService.getAllChecklist();
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Checklist Fetched Successfully",
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
    @PostMapping("/getChecklistById")
    public ResponseEntity<Map<String,Object>> getChecklistById(@RequestBody CheckListEntity inputChecklistId) {
        try{
            int checklistId = inputChecklistId.getCheckListId();
            List<Map<String, Object>> result = adminChecklistService.getChecklistById(checklistId);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Checklist Fetched Successfully",
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
    @PostMapping("/getChecklistByRole")
    public ResponseEntity<Map<String,Object>> getChecklistByRole(@RequestBody CheckListEntity inputRoleId) {
        try{
            int roleId = inputRoleId.getRoleId();
            List<Map<String, Object>> result = adminChecklistService.getChecklistByRole(roleId);
            return ResponseEntity.ok(Map.of(
                    STATUS, 200,
                    MESSAGE, "Checklist Fetched Successfully",
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
    @PostMapping("/createChecklist")
    public ResponseEntity<Map<String,Object>> createChecklist(@RequestBody CheckListEntity inputChecklis, HttpServletRequest request) {
        try{
            CheckListEntity data = adminChecklistService.createChecklist(inputChecklis,request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    STATUS, 201,
                    MESSAGE, "CheckList Created Successfully",
                    DATA, data
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    STATUS, 500,
                    MESSAGE, e.getMessage()
            ));
        }
    }
    @PutMapping("/updateChecklist")
    public ResponseEntity<Map<String, Object>> updateChecklist(@RequestBody CheckListEntity inputChecklis, HttpServletRequest request) {
        try{
            int checklistId = inputChecklis.getCheckListId();
            CheckListEntity updatedData = adminChecklistService.updateChecklist(checklistId, inputChecklis, request);
            return ResponseEntity.ok(Map.of(
                    STATUS, 201,
                    DATA, updatedData,
                    MESSAGE, "CheckList Updated Successfully"
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
