package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Entity.*;
import com.employee.onboarding.employee_onboarding_system.Exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.Repository.RoleListRepo;
import com.employee.onboarding.employee_onboarding_system.Repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.Repository.UserOnboardingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EobServiceImpl implements EobService {
    @Autowired
    RoleListRepo roleListRepo;
    @Autowired
    UserDetailsRepo userDetailsRepo;
    @Autowired
    UserOnboardingStatusRepo userOnboardingStatusRepo;
    @Override

    public List<RoleListEntity> getAllRoleList() {
        return roleListRepo.findAllVisibleRoles();
    }
    @Override
    public UserDetailsKey getAllUsersById(int id) {
        UserDetailsKey user = userDetailsRepo.findByUserId(id);
        if(user == null){
            throw new ResourceNotFoundException("No User records found for : " + id);
        }
        return user;
    }
    @Override
    public List<OnboardingKey> getChecklistForUser(int id){
        List<OnboardingKey> checkList = userOnboardingStatusRepo.findChecklistByUserId(id);
        if(checkList.isEmpty()){
            throw new ResourceNotFoundException("No CheckList records found for : " + id);
        }
        return checkList;
    }
    @Override
    public String setStatus(int userId,int checkListId,boolean status){
        int rowsUpdated = userOnboardingStatusRepo.findByUserAndChecklist(userId,checkListId,status);
        if(rowsUpdated == 0){
            throw new ResourceNotFoundException("No onboarding records found");
        }
        return "status updated : "+status;
    }
}
