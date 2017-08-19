package com.ayouris.tawassol.security.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.admin.model.beans.UserBean;
import com.ayouris.tawassol.admin.model.entity.QUser;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.impl.ListRepositoryImpl;
import com.ayouris.tawassol.security.repository.CustomUserRepository;

public class UserRepositoryImpl extends ListRepositoryImpl<User, QUser> implements CustomUserRepository ,GridRepositoryCustom<UserBean> {

	private final JPAQueryFactory queryFactory;
	private final QUser user = QUser.user;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	public UserRepositoryImpl(JPAQueryFactory queryFactory) {
		super();
		this.queryFactory = queryFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.findByName(username);
		if (null == user) {
			throw new UsernameNotFoundException("The user with name " + username + " was not found");
		}

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByName(String userName) {
		JPAQuery<User> query = queryFactory.selectFrom(user).where(user.username.eq(userName));
		return query.fetchOne();
	}

	@Override
	public GridListBean<UserBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QUser users = QUser.user;

		JPAQuery<User> query = queryFactory.selectFrom(users);

		// Sorting
		applySort(query, pageDataBean, users, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, users, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<User> userList = query.fetch();

		GridListBean<UserBean> result = new GridListBean<UserBean>();

		result.setTotalItems(totalCount);

		List<UserBean> usersBeans = mapper.map(userList, UserBean.LIST_BEAN_TYPE);
		result.getData().addAll(usersBeans);

		return result;
	}
}
