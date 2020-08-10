package com.example.authserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authserver.model.AuthUser;
import com.example.authserver.model.User;
import com.example.authserver.repository.UserRepository;

@Service("userDetailsService")
public class AuthServerDetails implements UserDetailsService {
	
	@Autowired 
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("username "+username);
		Optional<User> user=userRepo.findByUserName(username);
		
		System.out.println("username "+user.get().getUserName()+"  "+user.get().getPassword());;
		user.orElseThrow( ()-> new UsernameNotFoundException(" Username or Password not valid"));
		
		UserDetails authUser=new AuthUser(user.get());
		
		new AccountStatusUserDetailsChecker().check(authUser);
		
		return authUser ;
	}

}
