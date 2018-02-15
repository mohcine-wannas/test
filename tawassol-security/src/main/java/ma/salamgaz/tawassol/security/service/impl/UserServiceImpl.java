package ma.salamgaz.tawassol.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.salamgaz.tawassol.admin.model.entity.QUser;
import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.service.impl.BaseServiceImpl;
import ma.salamgaz.tawassol.security.predicate.UserPredicate;
import ma.salamgaz.tawassol.security.repository.UserRepository;
import ma.salamgaz.tawassol.security.service.UserService;


@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
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
