package com.ayouris.tawassol.common.model.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenData {

    private String token;

    public TokenData() {
    }

    public TokenData(String token) {
        this.token = token;
    }

}
