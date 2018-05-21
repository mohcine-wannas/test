package com.ayouris.tawassol.security.service;

import java.util.Map;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.service.BaseService;

public interface PasswordService extends BaseService<User> {

	Long passwordChange(Map<String, String> bean);

	Long passwordChange(User user, Map<String, String> bean);

	String encodePassword(String pwd);

}
