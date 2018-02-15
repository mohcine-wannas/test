package ma.salamgaz.tawassol.security.service.impl;

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

import ma.salamgaz.tawassol.admin.model.entity.QUser;
import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;
import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.service.impl.BaseServiceImpl;
import ma.salamgaz.tawassol.security.predicate.UserPredicate;
import ma.salamgaz.tawassol.security.repository.UserRepository;
import ma.salamgaz.tawassol.security.service.AuthenticateService;
import ma.salamgaz.tawassol.security.service.PasswordService;
import ma.salamgaz.tawassol.security.service.UserService;
import ma.salamgaz.tawassol.security.utils.SecurityUtils;


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
