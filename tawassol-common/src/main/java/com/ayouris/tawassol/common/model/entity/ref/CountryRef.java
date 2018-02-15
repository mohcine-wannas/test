package com.ayouris.tawassol.common.model.entity.ref;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.lang.CountryLang;

@Setter
@Entity
@Table(name = "country_ref", schema = "tawassol")
public class CountryRef extends RefData<CountryLang> {

    private static final long serialVersionUID = -1588111857537450611L;

    private String codeCourt;

    private Set<CountryLang> localizedLabel = new HashSet<>(0);

    @Column(name = "code_court")
    public String getCodeCourt() {
        return codeCourt;
    }


    @Override
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Set<CountryLang> getLocalizedLabel() {
        return localizedLabel;
    }

}
