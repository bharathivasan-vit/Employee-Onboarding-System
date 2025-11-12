package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Dto.ChecklistDTO;
import com.employee.onboarding.employee_onboarding_system.Dto.OnboardingDTO;
import com.employee.onboarding.employee_onboarding_system.Entity.OnboardingBulkRecordingKey;
import com.employee.onboarding.employee_onboarding_system.Exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.Repository.UserOnboardingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    UserOnboardingStatusRepo userOnboardingStatusRepo;
    public List<OnboardingBulkRecordingKey> getOnboardingBulkRecords(){
        return userOnboardingStatusRepo.findBulkOnboardingRecords();
    }
    public List<OnboardingBulkRecordingKey> getOnboardingBulkRecordsByEmailId(String emailId){
        List<OnboardingBulkRecordingKey> onboardingRecords = userOnboardingStatusRepo.findBulkOnboardingRecordsByEmailId(emailId);
        if(onboardingRecords.isEmpty()){
            throw new ResourceNotFoundException("No onboarding records found for email: "+emailId);
        }
        return onboardingRecords;
    }

    public List<OnboardingDTO> getOnboardingBulkRecordsClearView(){
        List<OnboardingBulkRecordingKey> records= userOnboardingStatusRepo.findBulkOnboardingRecords();
        Map<Integer,OnboardingDTO> onboardingMap = new LinkedHashMap<>();
        records.forEach(record->{
            onboardingMap.computeIfAbsent(record.getUserId(),id->new OnboardingDTO(
                    record.getUserId(),
                    record.getUserName(),
                    record.getPhoneNumber(),
                    record.getEmailId(),
                    record.getRoleName(),
                    new ArrayList<>()
            ));
            onboardingMap.get(record.getUserId()).getChecklists().add(new ChecklistDTO(record.getCheckListContent(),record.getChecklistDescription(),record.getStatus()));
        });
        return new ArrayList<>(onboardingMap.values());
    }

}
