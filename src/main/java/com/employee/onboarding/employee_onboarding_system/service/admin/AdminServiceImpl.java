package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserOnboardingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired UserOnboardingStatusRepo userOnboardingStatusRepo;
    @Autowired UserDetailsRepo userDetailsRepo;
    public List<Map<String,Object>> getOnboardingBulkRecords(){
        return userOnboardingStatusRepo.findBulkOnboardingRecords();
    }
    public List<Map<String,Object>> getOnboardingBulkRecordsClearView() {
        List<Map<String,Object>> records = userOnboardingStatusRepo.findBulkOnboardingRecords();
        List<Map<String,Object>> onboarding = new ArrayList<>();
        List<Long> userIdContainer = new ArrayList<>();
        for (Map<String, Object> userRecord : records) {
            Long userId = ((Number) userRecord.get("userid")).longValue();
            if (!userIdContainer.contains(userId)) {
                userIdContainer.add(userId);
                Map<String,Object> userMap = new LinkedHashMap<>();
                userMap.put("userId", userId);
                userMap.put("userName", userRecord.get("username"));
                userMap.put("emailId", userRecord.get("emailid"));
                userMap.put("phoneNumber", userRecord.get("phonenumber"));
                userMap.put("roleName", userRecord.get("rolename"));
                List<Map<String,Object>> checklist = new ArrayList<>();
                Map<String,Object> checklistMap = new LinkedHashMap<>();
                checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                checklistMap.put("status", userRecord.get("status"));
                checklistMap.put("createdDate", userRecord.get("createdDate"));
                checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));
                checklist.add(checklistMap);
                userMap.put("checkList", checklist);
                onboarding.add(userMap);
            } else {
                for (Map<String,Object> u : onboarding) {
                    if (u.get("userId").equals(userId)) {
                        List<Map<String,Object>> checklist = (List<Map<String,Object>>) u.get("checkList");
                        Map<String,Object> checklistMap = new LinkedHashMap<>();
                        checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                        checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                        checklistMap.put("status", userRecord.get("status"));
                        checklistMap.put("createdDate", userRecord.get("createdDate"));
                        checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));
                        checklist.add(checklistMap);
                        break;
                    }
                }
            }
        }
        return onboarding;
    }
    public List<Map<String,Object>> getAllUsersByVerified(){
        return userDetailsRepo.findUserByVerified();
    }
    public List<Map<String,Object>> getUsersByName(String userName){
        List<Map<String,Object>> users = userDetailsRepo.findUserByName(userName);
        if(users.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        return users;
    }
}
