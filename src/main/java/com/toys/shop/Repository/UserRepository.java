package com.toys.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findUserById(int id);
}
