package ma.salamgaz.gwic.security.repository;

import org.springframework.stereotype.Repository;

import ma.salamgaz.gwic.admin.model.entity.User;
import ma.salamgaz.gwic.common.repository.CommonRepository;

@Repository
public interface UserRepository extends CommonRepository<User>, CustomUserRepository {

	
}
