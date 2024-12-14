package com.phanvanto.cinema.DTO;

import java.sql.Date;

public class ChangeInfo {

	private String fullname;
	private Date birthday;
	private String telephone;
	private String email;
	public ChangeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangeInfo(String fullname, Date birthday, String telephone, String email) {
		super();
		this.fullname = fullname;
		this.birthday = birthday;
		this.telephone = telephone;
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
