package com.ayouris.tawassol.security.model;

import java.io.Serializable;

import com.ayouris.tawassol.common.util.BeanUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDetails implements Serializable {

    private static final long serialVersionUID = 3847205726858956918L;

    private String company;
    private String companyLegalForm;
    private Long organizationId;

    // Address
    private String adresse;
    private String additionalAddress;
    private String city;
    private String zipCode;
    private String countryLabel;
    private String countryCode;

    private String phoneNumber;
    private String mobileNumber;
    private String email;



    public String getCompleteAddress() {
        return BeanUtils.getCompleteAddress(adresse, additionalAddress, zipCode, city, countryLabel, countryCode);
    }

}
