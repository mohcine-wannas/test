package ma.salamgaz.tawassol.security.repository;

import org.springframework.stereotype.Repository;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.repository.CommonRepository;

@Repository
public interface UserRepository extends CommonRepository<User>, CustomUserRepository {

	
}
