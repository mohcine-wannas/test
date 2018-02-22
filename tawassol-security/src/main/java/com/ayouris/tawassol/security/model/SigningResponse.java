package com.ayouris.tawassol.security.model;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class SigningResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -941215881986272862L;

	private SigningResponseStatus responseStatus;

    private Certificate[] chain;

    private PrivateKey key ;

    public SigningResponse() {
    }

}
