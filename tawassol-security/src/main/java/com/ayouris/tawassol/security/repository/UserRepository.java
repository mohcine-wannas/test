package com.ayouris.tawassol.security.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.repository.CommonRepository;

@Repository
public interface UserRepository extends CommonRepository<User>, CustomUserRepository  {

	List<User> findBySiteId(Long userId);
	List<User> findByUsername(String login);
	List<User> findByMatricule(String matricule);
	
}
