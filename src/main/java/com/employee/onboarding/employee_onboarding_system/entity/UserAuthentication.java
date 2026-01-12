package com.employee.onboarding.employee_onboarding_system.entity;

public class UserAuthentication {

	private String userId;
	private String passwd;
	private String authenticationMesage;
	private boolean authenticated;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAuthenticationMesage() {
		return authenticationMesage;
	}
	public void setAuthenticationMesage(String authenticationMesage) {
		this.authenticationMesage = authenticationMesage;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
	
}
