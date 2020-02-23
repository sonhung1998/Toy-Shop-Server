package com.toys.shop.Entities.Account;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toys.shop.Repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Account account = accountRepository.findByUserName(username);
		if (account == null) {
			throw new UsernameNotFoundException(username + "Is Not Found");
		}
		return new CustomAccountDetails(account);
	}

	@Transactional
	public UserDetails loadUserById(Integer id) {
		Account account = accountRepository.findAccountById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Account not found with id : " + id));

		return new CustomAccountDetails(account);
	}
}
