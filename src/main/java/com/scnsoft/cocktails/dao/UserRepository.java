package com.scnsoft.cocktails.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByLogin(String login);
}
