package com.qa.account.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.account.persistence.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
