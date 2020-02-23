package com.toys.shop.Entities.Account;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomAccountDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Account account;

	@SuppressWarnings("null")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("Admin"));
		roles.add(new SimpleGrantedAuthority("Customer"));
		return roles;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return account.getPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return account.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
