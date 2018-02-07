package ma.salamgaz.tawassol.security.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.tawassol.admin.model.entity.QUser;
import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.security.repository.CustomUserRepository;

public class UserRepositoryImpl implements CustomUserRepository {

	private final JPAQueryFactory queryFactory;
	private final QUser user = QUser.user;

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

}
