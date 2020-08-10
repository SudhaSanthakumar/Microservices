package com.example.authserver.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission implements  Serializable{
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="permission_id")
	private int permissionId;
	
	@Column(name="permission_name")
	private String permissionName;
	
	
	@ManyToMany(mappedBy="permissions")
	private List<Role> roles;

	public Permission() {
		super();
	}

	public Permission(int permissionId, String permissionName, List<Role> roles) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.roles = roles;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	

}
