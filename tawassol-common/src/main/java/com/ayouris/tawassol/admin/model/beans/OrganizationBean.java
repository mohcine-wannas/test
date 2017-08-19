package com.ayouris.tawassol.admin.model.beans;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import com.ayouris.tawassol.common.model.entity.ref.CountryRef;
import com.ayouris.tawassol.admin.model.entity.Organization;
import com.ayouris.tawassol.admin.model.enums.OrganizationType;



import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@SuppressWarnings("unused")
public class OrganizationBean {

    private Long id;

    private String name;

    private String acronym;

    private String parentOrganizationName;

    private Long parentOrganizationId;

    private String contactFirstname;

    private String contactLastname;

    private String contactEmail;

    private Date contactBirthdate;

    private CountryRef contactNationality;

    private String contactPositionHeld;

    private String address;

    private String additionalAddress;

    private String zipCode;

    private String city;

    private String phoneNumber;

    private String faxNumber;

    private String mobileNumber;

    private String legalForm;

    private Boolean status;

    private OrganizationType type;

    private OrganizationInfoBean parent;


    public static OrganizationBean convert(Organization org) {
        if (org == null) {
            return null;
        }
        OrganizationBean organization = new OrganizationBean();
        organization.setAcronym(org.getAcronym());
        organization.setAdditionalAddress(org.getAdditionalAddress());
        organization.setAddress(org.getAddress());
        organization.setCity(org.getCity());
        organization.setContactEmail(org.getEmail());
        organization.setContactFirstname(org.getManagerFirstname());
        organization.setContactLastname(org.getManagerLastname());
        organization.setContactBirthdate(org.getManagerBirthDate());
        organization.setContactPositionHeld(org.getManagerOccupation());
        organization.setFaxNumber(org.getFaxNumber());
        organization.setId(org.getId());
        organization.setName(org.getName());
        organization.setPhoneNumber(org.getPhoneNumber());
        organization.setZipCode(org.getZipCode());
        if (org.getParent() != null) {
            OrganizationInfoBean oi = new OrganizationInfoBean();
            oi.setId(org.getParent().getId());
            oi.setLabel(org.getParent().getName());
            oi.setCode(org.getParent().getAcronym());
            organization.setParentOrganizationName(org.getParent().getName());
            organization.setParentOrganizationId(org.getParent().getId());
            organization.setParent(oi);
        }
        organization.setStatus(org.getEnabled());
        organization.setMobileNumber(org.getMobileNumber());
        /*
        organization
                .setType(org instanceof FillingCenterRef ? OrganizationType.FILLING_CENTER
                        : org instanceof DealerRef ? OrganizationType.DEALER
                                : org instanceof CustomerRef ? OrganizationType.CUSTOMER
                                        : org null);
        if (org instanceof CustomerRef) {
        	CustomerRef customer = (CustomerRef) org;
            
            organization.setLegalForm(customer.getLegalForm());
            organization.setAgreementNumber(customer.getAgreementNumber());
        }
        */
        return organization;
    }
    public static Organization convert(OrganizationBean orgBean) {
        if (orgBean == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setAcronym(orgBean.getAcronym());
        organization.setAdditionalAddress(orgBean.getAdditionalAddress());
        organization.setAddress(orgBean.getAddress());
        organization.setCity(orgBean.getCity());
        organization.setEmail(orgBean.getContactEmail());
        organization.setManagerFirstname(orgBean.getContactFirstname());
        organization.setManagerLastname(orgBean.getContactLastname());
        organization.setManagerBirthDate(orgBean.getContactBirthdate());
        organization.setManagerOccupation(orgBean.getContactPositionHeld());
        organization.setFaxNumber(orgBean.getFaxNumber());
        organization.setId(orgBean.getId());
        organization.setName(orgBean.getName());
        organization.setPhoneNumber(orgBean.getPhoneNumber());
        organization.setZipCode(orgBean.getZipCode());
        if (orgBean.getParent() != null) {
        	//TODO
//            OrganizationInfoBean oi = new OrganizationInfoBean();
//            oi.setId(orgBean.getParent().getId());
//            oi.setLabel(orgBean.getParent().getName());
//            oi.setCode(orgBean.getParent().getAcronym());
//            
//            organization.setParentOrganizationName(orgBean.getParent().getName());
//            organization.setParentOrganizationId(orgBean.getParent().getId());
//            organization.setParent(oi);
        }
        organization.setEnabled(orgBean.getStatus());
        organization.setMobileNumber(orgBean.getMobileNumber());
        /*
        organization
                .setType(org instanceof FillingCenterRef ? OrganizationType.FILLING_CENTER
                        : org instanceof DealerRef ? OrganizationType.DEALER
                                : org instanceof CustomerRef ? OrganizationType.CUSTOMER
                                        : org null);
        if (org instanceof CustomerRef) {
        	CustomerRef customer = (CustomerRef) org;
            
            organization.setLegalForm(customer.getLegalForm());
            organization.setAgreementNumber(customer.getAgreementNumber());
        }
        */
        return organization;
    }
    public void populate(Organization org, Organization parent) {
        if (org == null) {
            return;
        }
        org.setAcronym(getAcronym());
        org.setAdditionalAddress(getAdditionalAddress() != null ? getAdditionalAddress() : "");
        org.setAddress(getAddress() != null ? getAddress() : "");
        org.setCity(getCity() != null ? getCity() : "");
        org.setEmail(getContactEmail());
        org.setEnabled(getStatus());
        org.setFaxNumber(getFaxNumber() != null ? getFaxNumber() : "");
        org.setManagerFirstname(WordUtils.capitalizeFully(getContactFirstname()));
        org.setManagerLastname(StringUtils.upperCase(getContactLastname()));
        org.setMobileNumber(getMobileNumber() != null ? getMobileNumber() : "");
        org.setName(getName());
        org.setParent(parent);
        org.setPhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : "");
        org.setZipCode(getZipCode() != null ? getZipCode() : "");
        org.setManagerBirthDate(getContactBirthdate());
        org.setManagerOccupation(getContactPositionHeld());
        /*
        if (org instanceof CustomerRef) {
            ((CustomerRef) org).setAgreementNumber(getAgreementNumber());
            ((CustomerRef) org).setLegalForm(getLegalForm());
        }
        */
    }

}
