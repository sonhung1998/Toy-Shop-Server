package com.toys.shop.Api.Account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.toys.shop.Entities.Account.Account;
import com.toys.shop.Repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class AccountResource {
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/account/{accountId}")
	public ResponseEntity<Account>getAccount(
			@PathVariable(name = "accountId") Integer id) throws Exception{
		Optional<Account> accountOptial=accountRepository.findById(id);
		if(!accountOptial.isPresent()) {
			throw new Exception("Không tồn tại Account với id:"+id);
		}
		Account accountToFind=accountOptial.get();
		
		return new ResponseEntity<Account>(accountToFind, HttpStatus.OK);
		
	}

}
