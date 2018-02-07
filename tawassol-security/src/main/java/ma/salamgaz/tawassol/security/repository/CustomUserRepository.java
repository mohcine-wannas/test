package ma.salamgaz.tawassol.security.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;

import ma.salamgaz.tawassol.admin.model.entity.User;

@NoRepositoryBean
public interface CustomUserRepository {

    UserDetails loadUserByUsername(String userName);

    User findByName(String name);
}
