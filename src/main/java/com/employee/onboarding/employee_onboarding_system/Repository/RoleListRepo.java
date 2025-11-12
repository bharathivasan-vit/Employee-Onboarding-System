package com.employee.onboarding.employee_onboarding_system.Repository;

import com.employee.onboarding.employee_onboarding_system.Entity.RoleListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleListRepo extends JpaRepository<RoleListEntity,Integer> {
    @Query(value = "select * from ipr.eob_role where is_active = true",nativeQuery = true)
    List<RoleListEntity> findAllVisibleRoles();
}
