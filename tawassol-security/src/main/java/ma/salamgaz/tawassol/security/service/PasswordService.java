package ma.salamgaz.tawassol.security.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.service.BaseService;

public interface PasswordService extends BaseService<User> {

	Long passwordChange(Map<String, String> bean);

}
