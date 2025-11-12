package com.employee.onboarding.employee_onboarding_system.Controller;

import com.employee.onboarding.employee_onboarding_system.Entity.*;
import com.employee.onboarding.employee_onboarding_system.Service.EobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {
        "http://localhost:5173/",
})
@RestController
@RequestMapping("/eobs/")
public class EobController {
    @Autowired
    EobService eobService;
    @GetMapping("getAllRoleList")
    public List<RoleListEntity> getAllRoleList(){
        return eobService.getAllRoleList();
    }
    @GetMapping("getAllUsersById/{userId}")
    public UserDetailsKey getAllUsersById(@PathVariable int userId){
        return eobService.getAllUsersById(userId);
    }
    @GetMapping("getChecklistForUser/{userId}")
    public List<OnboardingKey> getChecklistForUser(@PathVariable int userId) {
        return eobService.getChecklistForUser(userId);
    }
    @PutMapping("setStatus/{userId}/{checkListId}/{status}")
    public ResponseEntity<?> setStatus(@PathVariable int userId,@PathVariable int checkListId,@PathVariable boolean status){
        String result = eobService.setStatus(userId,checkListId,status);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", result
        ));
    }
    @PutMapping("setStatusTrue/{userId}/{checkListId}")
    public ResponseEntity<?> setStatusTrue(@PathVariable int userId,@PathVariable int checkListId){
        String result = eobService.setStatus(userId,checkListId,true);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", result
        ));
    }

}
