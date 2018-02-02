package ma.salamgaz.gwic.security.utils;

import java.io.Serializable;
import java.util.List;

import ma.salamgaz.gwic.security.model.UserContext;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
class UserContextImpl implements Serializable, UserContext {

    private final long id;
    private final String username;
    private final List<String> roles;

    UserContextImpl(long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
