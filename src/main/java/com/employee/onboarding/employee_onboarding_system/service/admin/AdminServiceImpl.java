package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserOnboardingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<Map<String,Object>> checklist = new ArrayList<>();
        List<Long> userIdContainer = new ArrayList<>();

        for (Map<String, Object> userRecord : records) {
            Long userId = (Long) userRecord.get("userid");
            if (!userIdContainer.contains(userId)) {
                userIdContainer.add(userId);
                Map<String,Object> userMap = new LinkedHashMap<>();
                userMap.put("userId", userRecord.get("userid"));
                userMap.put("userName", userRecord.get("username"));
                userMap.put("emailId", userRecord.get("emailid"));
                userMap.put("phoneNumber", userRecord.get("phonenumber"));
                userMap.put("roleName", userRecord.get("rolename"));
                userMap.put("checkList", checklist);

                Map<String,Object> checklistMap = new LinkedHashMap<>();
                checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                checklistMap.put("status", userRecord.get("status"));
                checklistMap.put("createdDate", userRecord.get("createdDate"));
                checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));
                checklist.add(checklistMap);

                onboarding.add(userMap);
            } else {
                Map<String,Object> checklistMap = new LinkedHashMap<>();
                checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                checklistMap.put("status", userRecord.get("status"));
                checklistMap.put("createdDate", userRecord.get("createdDate"));
                checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));

                checklist.add(checklistMap);
            }
        }
        return onboarding;
    }

    public List<Map<String,Object>> getOnboardingBulkRecordsByFilter(LocalDate startDate,LocalDate endDate,Boolean status,Integer roleId,String emailId){
        List<Map<String,Object>> records= userOnboardingStatusRepo.findBulkOnboardingRecordsByFilter(startDate,endDate,status,roleId,emailId);
        List<Map<String,Object>> onboarding = new ArrayList<>();
        List<Map<String,Object>> checklist = new ArrayList<>();
        List<Long> userIdContainer = new ArrayList<>();

        for (Map<String, Object> userRecord : records) {
            Long userId = (Long) userRecord.get("userid");
            if (!userIdContainer.contains(userId)) {
                userIdContainer.add(userId);
                Map<String,Object> userMap = new LinkedHashMap<>();
                userMap.put("userId", userRecord.get("userid"));
                userMap.put("userName", userRecord.get("username"));
                userMap.put("emailId", userRecord.get("emailid"));
                userMap.put("phoneNumber", userRecord.get("phonenumber"));
                userMap.put("roleName", userRecord.get("rolename"));
                userMap.put("checkList", checklist);

                Map<String,Object> checklistMap = new LinkedHashMap<>();
                checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                checklistMap.put("status", userRecord.get("status"));
                checklistMap.put("createdDate", userRecord.get("createdDate"));
                checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));
                checklist.add(checklistMap);

                onboarding.add(userMap);
            } else {
                Map<String,Object> checklistMap = new LinkedHashMap<>();
                checklistMap.put("checkListContent", userRecord.get("checkListContent"));
                checklistMap.put("checklistDescription", userRecord.get("checklistDescription"));
                checklistMap.put("status", userRecord.get("status"));
                checklistMap.put("createdDate", userRecord.get("createdDate"));
                checklistMap.put("modifiedDate", userRecord.get("modifiedDate"));

                checklist.add(checklistMap);
            }
        }
        return onboarding;
    }

    public List<Map<String,Object>> getAllUsersByVerified(){
        return userDetailsRepo.findUserByVerified();
    }

}
