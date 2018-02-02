package ma.salamgaz.tawassol.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.Categorie;
import ma.salamgaz.tawassol.common.model.helper.bean.EntityIdResolver;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = AdherentCriteriaDto.class)
public class AdherentCriteriaDto implements Serializable {

    private Long id;

    /**
     * Nom d'adhérent
     */
    private String nom;

    /**
     * Prénom d'adhérent
     */
    private String prenom;

    /**
     * CIN
     */
    private String cin;

    /**
     * Date de naissance
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    /**
     * Sexe
     */
    private String sexe;

    /**
     * Téléphone
     */
    private String tel;

    /**
     * Email
     */
    private String email;

    /**
     * Adresse
     */
    private String adresse;

    /**
     * categorie
     */
    @JsonIdentityReference(alwaysAsId = false)
    private Categorie categorie;

    /**
     * Nationalité
     */
    private String nationalite;

}