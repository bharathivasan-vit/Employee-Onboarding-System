package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.entity.RoleListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.repository.RoleListRepo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AdminRoleServiceImpl implements AdminRoleService{
    @Autowired
    RoleListRepo roleListRepo;

    public List<Map<String,Object>> getAllRole() throws ResourceNotFoundException{
        List<Map<String,Object>> roles = roleListRepo.findAllRoles();
        if(roles.isEmpty()){
            throw new ResourceNotFoundException("Role not found");
        }
        return roles;
    }
    public List<Map<String,Object>> getRoleById(int roleId) throws ResourceNotFoundException{
        List<Map<String,Object>> roles = roleListRepo.findRolesById(roleId);
        if(roles.isEmpty()){
            throw new ResourceNotFoundException("Role not found");
        }
        return roles;
    }
    public RoleListEntity createRole(RoleListEntity inputRole,HttpServletRequest request) throws NullPointerException{
            if (inputRole.getRoleName().isEmpty()) {
                throw new NullPointerException("Role input is null");
            }
            String ipAddress = request.getRemoteAddr();
            RoleListEntity role = new RoleListEntity();
            role.setRoleName(inputRole.getRoleName());
            role.setCreatedUser(inputRole.getCreatedUser());
            role.setCreatedDate(LocalDateTime.now());
            role.setModifiedUser(inputRole.getCreatedUser());
            role.setModifiedDate(LocalDateTime.now());
            role.setIpAddress(ipAddress);
            role.setActive(inputRole.isActive());

            return roleListRepo.save(role);
    }

    public RoleListEntity updateRole(int roleId, RoleListEntity inputRole, HttpServletRequest request) throws NullPointerException,ResourceNotFoundException{
        String ipAddress = request.getRemoteAddr();
        RoleListEntity existingRole = roleListRepo.findByRoleIdUpdate(roleId);
        if(null != existingRole){
            if (inputRole.getRoleName().isEmpty()){
                throw new NullPointerException("Role input is null");
            }
            existingRole.setRoleName(inputRole.getRoleName());
            existingRole.setModifiedUser(inputRole.getModifiedUser());
            existingRole.setModifiedDate(LocalDateTime.now());
            existingRole.setIpAddress(ipAddress);
            existingRole.setActive(inputRole.isActive());
        }else{
            throw new ResourceNotFoundException("Role not found");
        }
        return roleListRepo.save(existingRole);
    }
}
