package ma.salamgaz.tawassol.common.model.entity.generic;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class CoordinatesEntity extends AuditEntity {

    private static final long serialVersionUID = -4198913478694144050L;

    private String phoneNumber = "";
    private String email = "";
    private String faxNumber = "";
    private String mobileNumber = "";

    @Column(name = "phone_number", length = 50)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    @Column(name = "fax_number", length = 50)
    public String getFaxNumber() {
        return this.faxNumber;
    }

    @Column(name = "mobile_number", length = 50)
    public String getMobileNumber() {
        return this.mobileNumber;
    }

}
