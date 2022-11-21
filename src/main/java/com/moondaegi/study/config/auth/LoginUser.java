package com.moondaegi.study.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 어노테이션이 생성될 수 있는 위치를 지정, 파라미터 객체에만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
