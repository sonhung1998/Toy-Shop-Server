package com.toys.shop.Api;

import static com.toys.shop.Utils.LogUtils.LOGGER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Entities.Account.Account;
import com.toys.shop.Repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class AccountController {
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping(value= {"/","accounts"})
	public ResponseEntity<List<Account>>getAllAccount(){
		List<Account>accounts=accountRepository.findAll();
		LOGGER.info("List account:"+accounts);
		
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}

}
