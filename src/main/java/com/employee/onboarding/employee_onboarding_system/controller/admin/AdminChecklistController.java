package com.employee.onboarding.employee_onboarding_system.controller.admin;

import com.employee.onboarding.employee_onboarding_system.entity.CheckListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.service.admin.AdminChecklistService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
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
            return ResponseEntity.ok(createResponse(200,"Checklist Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/getChecklistById")
    public ResponseEntity<Map<String,Object>> getChecklistById(@RequestBody CheckListEntity inputChecklistId) {
        try{
            int checklistId = inputChecklistId.getCheckListId();
            List<Map<String, Object>> result = adminChecklistService.getChecklistById(checklistId);
            return ResponseEntity.ok(createResponse(200,"Checklist Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/getChecklistByName")
    public ResponseEntity<Map<String,Object>> getChecklistByName(@RequestBody CheckListEntity inputChecklistName) {
        try{
            String checklistName = inputChecklistName.getCheckListContent();
            List<Map<String, Object>> result = adminChecklistService.getChecklistByName(checklistName);
            return ResponseEntity.ok(createResponse(200,"Checklist Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/getChecklistByRole")
    public ResponseEntity<Map<String,Object>> getChecklistByRole(@RequestBody CheckListEntity inputRoleId) {
        try{
            int roleId = inputRoleId.getRoleId();
            List<Map<String, Object>> result = adminChecklistService.getChecklistByRole(roleId);
            return ResponseEntity.ok(createResponse(200,"Checklist Fetched Successfully",result));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PostMapping("/createChecklist")
    public ResponseEntity<Map<String,Object>> createChecklist(@RequestBody CheckListEntity inputChecklis, HttpServletRequest request) {
        try{
            CheckListEntity data = adminChecklistService.createChecklist(inputChecklis,request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createResponse(201,"CheckList Created Successfully",data));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponse(500,e.getMessage(),null));
        }
    }
    @PutMapping("/updateChecklist")
    public ResponseEntity<Map<String, Object>> updateChecklist(@RequestBody CheckListEntity inputChecklis, HttpServletRequest request) {
        try{
            int checklistId = inputChecklis.getCheckListId();
            CheckListEntity updatedData = adminChecklistService.updateChecklist(checklistId, inputChecklis, request);
            return ResponseEntity.ok(createResponse(201,"CheckList Updated Successfully",updatedData));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(404,e.getMessage(),null));
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
