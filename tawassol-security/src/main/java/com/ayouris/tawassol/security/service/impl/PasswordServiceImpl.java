package com.ayouris.tawassol.security.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.security.predicate.UserPredicate;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.AuthenticateService;
import com.ayouris.tawassol.security.service.PasswordService;
import com.ayouris.tawassol.security.service.UserService;
import com.ayouris.tawassol.security.utils.SecurityUtils;


@Transactional
@Service("passwordService")
public class PasswordServiceImpl extends BaseServiceImpl<User> implements PasswordService {

    private static final Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);
    
    @Autowired
	private AuthenticateService authenticateService;
    
    @Override
    public Long passwordChange(Map<String,String> bean) {
    	validate(bean);
    	User currentUser = SecurityUtils.getCurrentUser();
    	String oldPassword = bean.get("oldPassword");
    	
    	authenticateService.authenticateTest(currentUser.getUsername(),oldPassword);
        	String newPassword =encodePassword(bean.get("newPassword"));
        	currentUser.setPassword(newPassword);
    	save(currentUser);
    	return currentUser.getId();
    }

    private void validate(Map<String, String> bean) {
    	if(bean == null || StringUtils.isBlank(bean.get("oldPassword")) || StringUtils.isBlank(bean.get("newPassword"))) {
    		throw new ServiceException(ErrorMessageType.TMP_EXCEPTION.getMessage());
    	}
    }

    private String encodePassword(String pwd) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(pwd);
    }

}
