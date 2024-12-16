package com.phanvanto.cinema.DTO;

public class ChangePasswordDTO {

	public String password;
	public String newPassword;
	public ChangePasswordDTO(String password, String newPassword) {
		super();
		this.password = password;
		this.newPassword = newPassword;
	}
	public ChangePasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
