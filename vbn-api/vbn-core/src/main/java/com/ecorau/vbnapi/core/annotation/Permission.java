package com.ecorau.vbnapi.core.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD })
public @interface Permission {
	String value();
}
