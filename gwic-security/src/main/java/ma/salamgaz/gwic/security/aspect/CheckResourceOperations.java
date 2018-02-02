package ma.salamgaz.gwic.security.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ma.salamgaz.gwic.common.enums.OperationType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface CheckResourceOperations {

	String resource();
	OperationType[] operations();
}
