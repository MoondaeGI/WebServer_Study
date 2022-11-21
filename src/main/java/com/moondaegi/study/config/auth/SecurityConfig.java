package com.moondaegi.study.config.auth;

import com.moondaegi.study.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()  // 해당 옵션 비활성화
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()  // url별 권한 관리 설정(antMathers 사용가능)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // permitAll: 전체 열람 권한 제공
                .antMatchers("/api/**").hasRole(Role.USER.name())  // 권한 관리 대상을 지정하는 옵션, User만 사용 가능
                .anyRequest().authenticated()    // 설정값 이외의 url, authenticated: 인증된 사용자들에게만 허용
                .and()
                .logout().logoutSuccessUrl("/")  // 로그아웃 설정 진입, 성공시 이동하는 주소
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);  // 로그인 성공 이후 설정
    }
}
