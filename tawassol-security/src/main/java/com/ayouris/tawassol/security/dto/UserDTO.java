package com.ayouris.tawassol.security.dto;

import lombok.*;
import com.ayouris.tawassol.security.model.User;

@Builder
@ToString(exclude = {"user"})
@EqualsAndHashCode
public class UserDTO {

    private final User user;

    @Getter
    @Setter
    private Boolean isMyself = null;

    public long getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getNom();
    }


}
