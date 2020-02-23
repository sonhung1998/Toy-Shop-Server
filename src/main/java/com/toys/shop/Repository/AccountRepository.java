package com.toys.shop.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.Account.Account;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {
	@Query("From Account a JOIN FETCH a.role WHERE a.id =:id")
	Optional<Account> findAccountById(int id);
	
	Account findByUserName(String username);


}
