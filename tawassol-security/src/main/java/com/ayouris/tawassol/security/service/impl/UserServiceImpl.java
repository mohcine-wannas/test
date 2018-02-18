package com.ayouris.tawassol.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.security.predicate.UserPredicate;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.UserService;


@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
    protected UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository =userRepository;
	}
    

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    
    private UserRepository userRepository;

    @Autowired
    private UserPredicate userPredicate;
    
    @Autowired
    private CustomModelMapper mapper;
    
	@Override
    public UserDetails loadUserByUsername(String username) {
        return findUserByUsername(username);
    }

	@Cacheable(value = "memberCache", unless = "#result == null", key = "#username")
    @Override
    public User findUserByUsername(String username) {
        return userRepository.searchOneByEntityGraph(QUser.user.username.equalsIgnoreCase(username), QUser.user,
                "organization");
    }

}
