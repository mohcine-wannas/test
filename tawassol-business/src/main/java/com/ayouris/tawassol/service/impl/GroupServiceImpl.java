package com.ayouris.tawassol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GroupBean;
import com.ayouris.tawassol.common.model.entity.Group;
import com.ayouris.tawassol.service.GroupService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class GroupServiceImpl extends GenericServiceImpl<Group, Long> implements GroupService {


    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<GroupBean> getAll() {
        List<Group> client = findAll();
        return mapper.map(client, GroupBean.LIST_BEAN_TYPE);
    }


}
