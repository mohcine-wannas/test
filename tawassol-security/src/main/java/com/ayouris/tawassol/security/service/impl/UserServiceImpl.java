package com.ayouris.tawassol.security.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.enums.ContactType;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.DataFilterBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.UserDetailsBean;
import com.ayouris.tawassol.common.model.bean.UsersListBean;
import com.ayouris.tawassol.enums.Column;
import com.ayouris.tawassol.enums.Order;
import com.ayouris.tawassol.security.aspect.CheckOrganization;
import com.ayouris.tawassol.security.exception.ServiceSecurityException;
import com.ayouris.tawassol.security.predicate.UserPredicate;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.UserService;
import com.ayouris.tawassol.security.utils.SecurityUtils;



@Transactional
@Service("userService")
//@EnableJpaRepositories(basePackages = "com.ayouris.tawassol.security.repository", entityManagerFactoryRef = "com.ayouris.tawassol.common.repository.impl.CommonRepositoryFactoryBean")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPredicate userPredicate;
    
    @Autowired
    private CustomModelMapper mapper;
    
    @Override
    @CheckOrganization(name = { ContactType.SIEGE }) //TODO
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public User save(User member) {
        try {
            return userRepository.save(member);
        } catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public User findOne(Long memberId) {
        return userRepository.findOne(QUser.user.id.eq(memberId));
    }

    @Override
    public User update(User member) {
        return userRepository.save(member);
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public void delete(Long memberId) {
        userRepository.delete(memberId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return findUserByUsername(username);
    }

	@Cacheable(value = "memberCache", unless = "#result == null", key = "#username")
    @Override
    public User findUserByUsername(String username) {
        return userRepository.searchOneByEntityGraph(QUser.user.username.equalsIgnoreCase(username), QUser.user,
                "site");
    }
	
	@Override
	@SuppressWarnings("unchecked")
    public UsersListBean list(PageDataBean paginateData) {
        OrderSpecifier<?> order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.lastname.desc()
                : QUser.user.lastname.asc();
        Integer pageNumber = null;
        Integer pageSize = null;
        List<DataFilterBean> filters = paginateData.getFilters();
        if (paginateData != null) {
            pageNumber = paginateData.getPageNumber();
            pageSize = paginateData.getPageSize();
            if (paginateData.getSortColumn() != null) {
                if ("role.label".equals(paginateData.getSortColumn())) {
                    order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.roles.any().name.desc()
                            : QUser.user.roles.any().name.asc();
                } else {
                    switch (Column.valueOf(paginateData.getSortColumn())) {
                    case organizationname:
                        order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.organization.name.desc()
                                : QUser.user.organization.name.asc();
                        break;
                    case status:
                        order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.enabled.asc()
                                : QUser.user.enabled.desc();
                        break;
                    case firstname:
                        order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.firstname.desc()
                                : QUser.user.firstname.asc();
                        break;
                    case username:
                        order = Order.DESC.equals(paginateData.getSortOrder()) ? QUser.user.username.desc()
                                : QUser.user.username.asc();
                        break;
                    case lastname:
                    default:

                        break;
                    }
                }
            }
        }
        BooleanExpression pred = userPredicate.match(filters);
        Role mainRole = SecurityUtils.getCurrentUser().getRoles().iterator().next();
        BooleanExpression predFilter = mainRole.getRank() == 300 ? QUser.user.roles.any().rank.goe(mainRole.getRank())
                : QUser.user.roles.any().rank.gt(mainRole.getRank());
        if (pred != null) {
            pred = pred.and(predFilter);
        } else {
            pred = predFilter;
        }
        
		Iterable<User> liste = userRepository.searchOrderedByEntityGraph(pred, QUser.user, null, pageNumber, pageSize,
                order);
        long count = userRepository.count(pred);
        UsersListBean result = new UsersListBean();
        result.setTotalItems(Long.valueOf(count));
        for (User user : liste) {
            result.getData().add(UserDetailsBean.convertFull(user));
        }
        return result;
    }

    @Override
    public UserDetailsBean getUserDetails(Long id) {

        User user = userRepository.searchOneByEntityGraph(userPredicate.idEquals(id), QUser.user, "organization");
        //List<RoleBean> rolesBeans = roleService.getOrganizationRoles(user.getOrganization());

        return UserDetailsBean.convertFull(user/*, rolesBeans*/);
    }
    
    @Override
    public List<UserDetailsBean> getAll() {
        List<User> users = findAll();
        return mapper.map(users, UserDetailsBean.LIST_BEAN_TYPE);
    }
    
    @Override
    public List<UserDetailsBean> getBySite(Long userId){
        List<User> users = userRepository.findBySiteId(userId);
        return mapper.map(users, UserDetailsBean.LIST_BEAN_TYPE);
    }

}