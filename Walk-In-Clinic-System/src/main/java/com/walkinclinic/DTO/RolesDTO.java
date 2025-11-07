package com.walkinclinic.DTO;

public class RolesDTO {
	
	private Integer roleid;
	private String rolename;
	
	public Integer getRoleid() {
		return roleid;
	}
	
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	private RolesDTO(Integer roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	
	private RolesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
