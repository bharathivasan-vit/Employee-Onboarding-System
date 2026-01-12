/**
 * 
 */
package com.employee.onboarding.employee_onboarding_system.entity;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author admin
 *
 */
@Component
@SessionScope
public class User {
	
	private String userId;
	private String ipAddress;
	private String isAdmin;
	private String isReviewAppMapped;
	private String userRoleId;
	private String userRoleName;
	private BigDecimal costCentreId;
	private Integer defaultCostCenterId;
	private String costCenterCode;
	
	public BigDecimal getCostCentreId() {
		return costCentreId;
	}
	public void setCostCentreId(BigDecimal costCentreId) {
		this.costCentreId = costCentreId;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIsReviewAppMapped() {
		return isReviewAppMapped;
	}
	public void setIsReviewAppMapped(String isReviewAppMapped) {
		this.isReviewAppMapped = isReviewAppMapped;
	}
	public Integer getDefaultCostCenterId() {
		return defaultCostCenterId;
	}
	public void setDefaultCostCenterId(Integer defaultCostCenterId) {
		this.defaultCostCenterId = defaultCostCenterId;
	}
	public String getCostCenterCode() {
		return costCenterCode;
	}
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	
}
