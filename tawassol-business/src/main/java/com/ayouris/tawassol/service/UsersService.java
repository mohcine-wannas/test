package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.beans.UserBean;
import com.ayouris.tawassol.admin.model.entity.User;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
public interface UsersService extends GenericService<User,Long>,
        RefService<UserBean> {
    List<UserBean> getAll();

	String generatePassword(Long id);
}
