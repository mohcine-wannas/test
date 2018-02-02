package ma.salamgaz.gwic.common.model.entity.lang;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country_lang", schema = "gwic")
public class CountryLang extends RefDataLang {

    private static final long serialVersionUID = -3147521829434204423L;

}
