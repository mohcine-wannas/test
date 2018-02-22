package com.ayouris.tawassol.admin.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ayouris.tawassol.common.model.entity.NameDescriptionData;

@Entity
@Table(name = "operation", schema = "tawassol")
public class Operation extends NameDescriptionData {

    private static final long serialVersionUID = 6180862003399603896L;

}
