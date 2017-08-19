package com.ayouris.tawassol.security.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ayouris.tawassol.admin.model.entity.Organization;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.admin.model.enums.SiteType;
import com.ayouris.tawassol.common.enums.ContactType;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.security.model.MemberDetails;
import com.ayouris.tawassol.security.model.PermissionModel;
import com.ayouris.tawassol.security.model.UserContext;
import com.ayouris.tawassol.security.model.UserContextResponse;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentLogin() {

        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    return ((UserDetails) authentication.getPrincipal()).getUsername();
                } else if (authentication.getPrincipal() instanceof String) {
                    return (String) authentication.getPrincipal();
                }
            }
        }
        return null;
    }

    public static String getFirstAndLastName() {
        User user = getCurrentUser();
        return user.getFirstname() + " " + user.getLastname();
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() != null) {

            Object principal = securityContext.getAuthentication().getPrincipal();
            if (principal instanceof String && ((String) principal).equals(AuthoritiesConstants.ANONYMOUS)) {
                return false;
            }

            Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
            if (authorities != null) {
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * If the current user has a specific security role.
     */
    public static boolean hasRole(String role) {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(role));
                }
            }
        }
        return false;
    }

    /**
     *
     * If the current user has a any role in the specific security roles.
     */
    public static boolean hasAnyRole(String[] roles) {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    for (String role : roles) {
                        if (springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(role))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @return list of roles for the current user
     */
    public static List<String> getRolesForCurrentUser() {
        return extractRoles(getCurrentUserDetails());
    }

    /**
     *
     * @param userDetails
     * @return
     */
    public static List<String> extractRoles(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        return roles;
    }

    /**
     *
     * @return the current user details
     */
    public static UserDetails getCurrentUserDetails() {

        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     *
     * @return the current user context
     */
    public static UserContext getCurrentUserContext() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails == null) {
            return null;
        }
        UserContext userContext = new UserContextImpl(userDetails.hashCode(), userDetails.getUsername(),
                extractRoles(userDetails));
        return userContext;
    }

    /**
     *
     * @return the current user context
     */
    public static User getCurrentUser() {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public static UserContextResponse getCurrentUserContextResponse(User member) {

        MemberDetails details = new MemberDetails();

//        // Organisation info
//        Organization organization = member.getOrganization();
//        details.setAdresse(organization.getAddress());
//        details.setAdditionalAddress(organization.getAdditionalAddress());
//        details.setCity(organization.getCity());
//        details.setCountryLabel(organization.getCountryRef().getLabel());
//        details.setCountryCode(organization.getCountryRef().getCode());
//        details.setCompany(organization.getName());
//        details.setZipCode(organization.getZipCode());
//        details.setOrganizationId(organization.getId());
//        

        // Member info
        details.setPhoneNumber(member.getPhoneNumber());
        details.setMobileNumber(member.getMobileNumber());
        details.setEmail(member.getEmail());
        details.setActive(member.getActive());
        if(member.getSite() != null ) {
        	details.setSiteId(member.getSite().getId());
        	details.setSiteLibelle(member.getSite().getLibelle());
        	details.setSiteType(member.getSite().getSiteType());
        }
        PermissionModel model;
        ContactType type = getOrganizationType(member.getOrganization());
        /*if (member.getOrganization() instanceof Customer) {
            Customer customer = (Customer) member.getOrganization();
            model = PermissionUtils.createPermissionModel(member.getId(), ContactType.FILLING_CENTER.name(),
                    member.getRoles(), type, false, false);
        } else {
        */
            model = PermissionUtils.createPermissionModel(member.getId(), null,
                    member.getRoles(), type);
        //}
            //TODO test on null
        return new UserContextResponse(member.getId(), member.getUsername(),
                member.getFirstname(), member.getLastname(), details, model,member.getActive());
    }

    private static ContactType getOrganizationType(Organization orga) {
        ContactType result = null;
        /*
        if (orga instanceof FillingCenter) {
            result = ContactType.FILLING_CENTER;
        } else if (orga instanceof Dealer) {
            result = ContactType.DEALER;
        } else if (orga instanceof CustomerRef) {
            result = ContactType.CUSTOMS;
        }
        */
        return result;
    }

    public static UserContextResponse getCurrentUserContextResponse() {
        return getCurrentUserContextResponse(getCurrentUser());
    }

    public static Organization getCurrentUserOrganization() {
        return getCurrentUser().getOrganization();
    }
    
    public static Site getCurrentUserSite() {
        return getCurrentUser().getSite();
    }

    public static boolean isCurrentUserMemberOfSiege() {
        return SiteType.SIEGE.equals(getCurrentUserSite().getSiteType());
    }

    public static PermissionModel getCurrentUserPermissionModel() {
        User member = getCurrentUser();
        ContactType type = getOrganizationType(member.getOrganization());
        /*if (member.getOrganization() instanceof Customer) {
        Customer customer = (Customer) member.getOrganization();
        model = PermissionUtils.createPermissionModel(member.getId(), ContactType.FILLING_CENTER.name(),
                member.getRoles(), type);
    	} else {
        */
        return PermissionUtils.createPermissionModel(member.getId(), member.getContactType().name(), member.getRoles(),
                type);
    }

    public static boolean checkEnableAccount(UserDetails user) {
        return user.isAccountNonLocked() || user.isEnabled() || user.isAccountNonExpired()
                || user.isCredentialsNonExpired();
    }

    public static String getCurrentUserLang() {
        return getCurrentUser().getLang();
    }
}