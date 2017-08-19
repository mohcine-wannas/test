package com.ayouris.tawassol.security.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class UserData implements Serializable {

    private final boolean useOpenam;
    private final String token;
    private final UserContextResponse user;

    public UserData(boolean useOpenam, String token, UserContextResponse user) {
        this.useOpenam = useOpenam;
        this.token = token;
        this.user = user;
    }

    public boolean isUseOpenam() {
        return useOpenam;
    }

	public boolean isInactif() {
		return user.getActive() == null || !user.getActive() ;
	}

}
