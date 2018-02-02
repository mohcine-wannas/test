package ma.salamgaz.tawassol.admin.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ma.salamgaz.tawassol.common.mapper.NotNullable;
import ma.salamgaz.tawassol.common.util.BeanUtils;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ma.salamgaz.tawassol.common.model.entity.generic.CoordinatesEntity;
import ma.salamgaz.tawassol.common.model.entity.ref.CountryRef;

@Setter
@Entity
@Table(name = "organization", schema = "tawassol")
@Inheritance(strategy = InheritanceType.JOINED)
@NotNullable
public class Organization extends CoordinatesEntity {

    private static final long serialVersionUID = -194347450949189604L;

    private Organization parent;

    private String name;
    private String otherName;
    private String acronym;
    //private String edmLogoReference;

    private String address = "";
    private String additionalAddress = "";
    private String zipCode = "";
    private String city = "";
    private CountryRef countryRef;

    private String managerLastname = "";
    private String managerFirstname = "";
    private String managerOccupation = "";
    private Date managerBirthDate;

    private Date validityDate;
    private Boolean enabled = Boolean.TRUE;

    private List<Organization> children = new ArrayList<Organization>();
    private Set<User> users = new HashSet<User>(0);


    @Transient
    private String completeAddress;

    @Transient
//  /  @JsonBackReference
    //@ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "organization_parent_id")
    public Organization getParent() {
        return this.parent;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

   
    @Column(name = "other_name", length = 200)
    public String getOtherName() {
        return otherName;
    }

   
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return this.enabled;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validity_date", length = 13)
    public Date getValidityDate() {
        return this.validityDate;
    }

    @Column(name = "address", length = 128)
    public String getAddress() {
        return this.address;
    }

   
    @Column(name = "additional_address", length = 128)
    public String getAdditionalAddress() {
        return this.additionalAddress;
    }

  
    @Column(name = "zip_code", length = 10)
    public String getZipCode() {
        return this.zipCode;
    }

    @Column(name = "city", length = 35)
    public String getCity() {
        return this.city;
    }

  
    @Transient
    //@JsonManagedReference
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = { CascadeType.ALL })
    public List<Organization> getChildren() {
        return this.children;
    }

  
    /*
    @Column(name = "edm_logo_reference", length = 35)
    public String getEdmLogoReference() {
        return this.edmLogoReference;
    }
    */

    @Column(name = "acronym", length = 50)
    public String getAcronym() {
        return acronym;
    }

   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    public CountryRef getCountryRef() {
        return countryRef;
    }

   
    @Column(name = "manager_lastname")
    public String getManagerLastname() {
        return managerLastname;
    }

   
    @Column(name = "manager_firstname")
    public String getManagerFirstname() {
        return managerFirstname;
    }

   
    @Column(name = "manager_occupation")
    public String getManagerOccupation() {
        return managerOccupation;
    }

   
    @Column(name = "manager_birth_date")
    public Date getManagerBirthDate() {
        return managerBirthDate;
    }

   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Set<User> getUsers() {
        return users;
    }

    
    @Transient
    public void setCompleteAddress() {
        // nothing to do. used only to avoid mapping issue.
    }

    @Transient
    public String getCompleteAddress() {
        return BeanUtils.getCompleteAddress(address, additionalAddress, zipCode, city, countryRef.getLabel(),
                countryRef.getCode());
    }

}
