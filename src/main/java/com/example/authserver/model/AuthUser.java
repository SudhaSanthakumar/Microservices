package com.example.authserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser extends User implements UserDetails ,  Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthUser(User user) {
		super(user);
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> ga=new ArrayList<GrantedAuthority>();
		
		super.getRoles().forEach(role->{
			ga.add(new SimpleGrantedAuthority(role.getRoleName()));
			role.getPermissions().forEach(permission->{
				ga.add(new SimpleGrantedAuthority(permission.getPermissionName()));
			});
		});
		
		
		return ga;
	}

	@Override
	public String getPassword() {
		
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
