package com.stryde.webservice.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stryde.webservice.model.domain.User;
import com.stryde.webservice.model.enums.UserState;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

	Optional<User> findByEmailAndState(String email, UserState userState);

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
}