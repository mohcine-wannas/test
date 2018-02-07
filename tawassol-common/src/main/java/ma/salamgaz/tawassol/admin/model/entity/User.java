package ma.salamgaz.tawassol.admin.model.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ma.salamgaz.tawassol.common.enums.ContactChannel;
import ma.salamgaz.tawassol.common.enums.ContactType;
import ma.salamgaz.tawassol.common.mapper.NotNullable;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.salamgaz.tawassol.common.model.entity.generic.CoordinatesEntity;

@Setter
@Entity
@Table(name = "member", schema = "tawassol", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@NotNullable
public class User extends CoordinatesEntity implements UserDetails {

    private static final long serialVersionUID = -4320600165090121185L;

    private String lastname;
    private String firstname;
    private ContactType contactType;
    private ContactChannel preferredChannel;
    private String description;

    private String username;
    private String password;
    private boolean enabled;

    private Set<Role> roles = new HashSet<Role>(0);

    private String lang;

    @Override
    @Column(name = "username", unique = true, nullable = false, length = 128)
    public String getUsername() {
        return this.username;
    }

    @Override
    @Column(name = "password", nullable = false, length = 128)
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = "tawassol", joinColumns = { @JoinColumn(name = "member_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    public Set<Role> getRoles() {
        return this.roles;
    }

    @Column(name = "lastname", length = 128)
    public String getLastname() {
        return this.lastname;
    }

    @Column(name = "firstname", length = 128)
    public String getFirstname() {
        return this.firstname;
    }

    @Column(name = "contact_type", length = 128)
    @Enumerated(EnumType.STRING)
    public ContactType getContactType() {
        return this.contactType;
    }

    @Column(name = "preferred_channel", length = 35)
    @Enumerated(EnumType.STRING)
    public ContactChannel getPreferredChannel() {
        return this.preferredChannel;
    }

    @Column(name = "description")
    public String getDescription() {
		return description;
	}

    @Transient
    @Override
    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }

        Set<GrantedAuthority> autorities = new HashSet<GrantedAuthority>();

        for (Role role : roles) {
            autorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return autorities;
    }

    @Transient
    @JsonIgnore
    public Set<PermissionRight> getPermissions() {
        Set<PermissionRight> perms = new HashSet<PermissionRight>();
        for (Role role : roles) {
         //   perms.addAll(role.getPermissions());
        }
        return perms;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Transient
    @JsonIgnore
    public String getLang() {
        return lang;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(lastname);
        builder.append(firstname);
        builder.append(contactType);
        builder.append(username);
        builder.append(password);
        builder.append(enabled);
        return builder.toString();
    }

}
