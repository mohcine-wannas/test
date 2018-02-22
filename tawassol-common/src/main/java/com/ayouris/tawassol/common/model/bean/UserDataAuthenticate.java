package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@SuppressWarnings("serial")
public class UserDataAuthenticate implements Serializable {

    private char[] password;

    private String username;

    public UserDataAuthenticate() {
    }

    public UserDataAuthenticate(char[] password, String username) {
        this.password = password;
        this.username = username;
    }

}
