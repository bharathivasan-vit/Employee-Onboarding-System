package com.employee.onboarding.employee_onboarding_system.Controller;

import com.employee.onboarding.employee_onboarding_system.Dto.OnboardingDTO;
import com.employee.onboarding.employee_onboarding_system.Entity.OnboardingBulkRecordingKey;
import com.employee.onboarding.employee_onboarding_system.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/eobs/")
public class AdminController {
    @Autowired
    AdminService adminService;
    @GetMapping("getOnboardingBulkRecords")
    public List<OnboardingBulkRecordingKey> getOnboardingBulkRecords(){
        return adminService.getOnboardingBulkRecords();
    }
    @GetMapping("getOnboardingBulkRecordsByEmailId/{emailId}")
    public List<OnboardingBulkRecordingKey> getOnboardingBulkRecordsByEmailId(@PathVariable String emailId){
        return adminService.getOnboardingBulkRecordsByEmailId(emailId);
    }

    @GetMapping("getOnboardingBulkRecordsClearView")
    public List<OnboardingDTO> getOnboardingBulkRecordsClearView(){
        return adminService.getOnboardingBulkRecordsClearView();
    }
}
