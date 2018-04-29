package com.ayouris.tawassol.security.service.impl;

import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.service.impl.BaseServiceImpl;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserRepository userRepository;

    @Autowired
    protected UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @SuppressWarnings("unused")
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

    @Override
    public void setFCMToken(Long id, String token, Boolean isMobile) {
        List<User> userList = findUsersByFcmToken(token, isMobile);

        for (User userEntity : userList) {
            if (isMobile) {
                userEntity.setFcmMobileToken(null);
            } else {
                userEntity.setFcmWebToken(null);
            }
            userRepository.save(userEntity);
        }

        User entity = userRepository.findOne(id);

        if (entity != null) {
            if (isMobile) {
                entity.setFcmMobileToken(token);
            } else {
                entity.setFcmWebToken(token);
            }

            userRepository.save(entity);
        }

    }

    @Override
    public void clearFCMToken(Long id, Boolean isMobile) {
        User entity = userRepository.findOne(id);
        if (entity != null) {
            if (isMobile) {
                entity.setFcmMobileToken(null);
            } else {
                entity.setFcmWebToken(null);
            }

            userRepository.save(entity);
        }

    }

    private List<User> findUsersByFcmToken(String token, Boolean isMobile) {
        QUser user = QUser.user;
        List<User> users;
        if (isMobile) {
            users = (List<User>) userRepository.findAll(user.fcmMobileToken.eq(token));
        } else {
            users = (List<User>) userRepository.findAll(user.fcmWebToken.eq(token));
        }
        return users;
    }

}
