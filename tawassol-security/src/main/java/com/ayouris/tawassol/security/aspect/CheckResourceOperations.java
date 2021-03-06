package com.ayouris.tawassol.security.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ayouris.tawassol.common.enums.OperationType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface CheckResourceOperations {

	String resource();
	OperationType[] operations();
}
