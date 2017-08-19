package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.beans.UserBean;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.service.ProfilService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.UsersService;
import com.ayouris.tawassol.utils.PasswordGenerator;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
@Service
public class UsersServiceImpl extends GenericServiceImpl2<User,Long,UserBean> implements UsersService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private ProfilService profilService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public Long createOrUpdate(UserBean userBean) {
        validateRequiredValues(userBean);

        ValidateUsernameUnicity(userBean);
        validateMatriculeUnicity(userBean);
        
    	PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().build();
    	userBean.setPassword(passwordGenerator.generate());



        User entity = mapper.mapStrict(userBean, User.class);
    	//TODO Remove
        if (entity.getProfil() == null) {
            entity.setProfil(profilService.findByCode(12l));
        }
        
        try {
            return save(entity).getId();
        }catch (DataIntegrityViolationException ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

	private void validateMatriculeUnicity(UserBean userBean) {
    	Long userID = userBean.getId();
    	String login =userBean.getUsername();
        List<User> users = userRepository.findByUsername(login);
        if(users.size() > 0 && userID == null){
            throw new ServiceException(ErrorMessageType.USER_LOGIN_ALREADY_EXIST);
        }
	}

	private void ValidateUsernameUnicity(UserBean userBean) {
		Long userID = userBean.getId();
        String matricule =userBean.getMatricule();
        List<User> usersMatricule = userRepository.findByMatricule(matricule);
        if(usersMatricule.size() > 0 && userID == null){
            throw new ServiceException(ErrorMessageType.USER_MATRICULE_ALREADY_EXIST);
        }
	}

    @Override
    public UserBean getDetails(Long id) {
        User user = findOne(id);
        return mapper.map(user, UserBean.class);
    }

    @Override
    public GridListBean<UserBean> list(PageDataBean paginateData) {
        return list(paginateData,ViewName.USERS);
    }

    @Override
    public List<UserBean> getAll() {
        Iterable<User> users = findAll();
        return mapper.map(users, UserBean.LIST_BEAN_TYPE);
    }

	@Override
	public String generatePassword(Long id) {
		//TODO PERMISSIONS
		if (id == null) {
			throw new ServiceException(ErrorMessageType.USER_ID_NOT_FOUND);
		}
		User entity = findOne(id);
		if (entity == null) {
			throw new ServiceException(ErrorMessageType.USER_ID_NOT_FOUND);
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().build();
		String pwd = passwordGenerator.generate();
		entity.setPassword(passwordEncoder.encode(pwd));

		try {
			save(entity).getId();
			return pwd;
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}
	
    private void validateRequiredValues(UserBean userBean) {
        if (userBean.getMatricule() ==null || userBean.getUsername() == null
                || userBean.getFirstname() == null || userBean.getLastname() == null
                || userBean.getEmail() == null
                || userBean.getSite() == null || userBean.getProfilType() == null) {

            throw new ServiceException(ErrorMessageType.UTILISATEUR_MISSING_REQUIRED_VALUES);
        }

    }
}
