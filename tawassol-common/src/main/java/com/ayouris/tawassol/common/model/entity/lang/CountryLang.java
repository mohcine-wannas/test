package com.ayouris.tawassol.common.model.entity.lang;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country_lang", schema = "tawassol")
public class CountryLang extends RefDataLang {

    private static final long serialVersionUID = -3147521829434204423L;

}
