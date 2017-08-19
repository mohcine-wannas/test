package com.ayouris.tawassol.common.model.bean;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersListBean {
    private List<UserDetailsBean> data = new ArrayList<>();
    private Long totalItems;
}
