package com.employee.onboarding.employee_onboarding_system.Repository;

import com.employee.onboarding.employee_onboarding_system.Entity.CheckListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CheckListRepo extends JpaRepository<CheckListEntity, Integer> {
    @Query(value = "SELECT check_list_id from ipr.eob_check_list where role_id = ?1 AND is_active = true",nativeQuery = true)
    List<Integer> findCheckListByRoleId(int id);
}
