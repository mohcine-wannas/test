package com.ayouris.tawassol.admin.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ayouris.tawassol.common.mapper.NotNullable;
import com.ayouris.tawassol.common.model.entity.NameDescriptionData;


@Entity
@Table(name = "resources", schema = "tawassol")
@NotNullable
public class Resource extends NameDescriptionData {

    private static final long serialVersionUID = -3146982904254663129L;
}
