package com.jojoldu.book.config.auth;

import com.jojoldu.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화 시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable() //h2-condole 화면을 사용하기 위해 해당 옵션들을 disable
                .and().authorizeRequests() //URL 별 권한 관리를 설정하는 옵션의 시작점, 이게 선언되어야만 antMatchers옵션 사용가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 권한관리 대상을 지정하는 옵션, URL, HTTP메소드별로 관리가 가능 , "/" 등 지정된 URL 들은 permitAll 옵션을 통해 전체 열람 권한을 주었음
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //POST 메소드 이면서 "/api/v1/**" 주소를 가진 API는 User 권한을 가진 사람만 가능하도록 설정
                .anyRequest().authenticated() //설정된 값들 이외의 URL들을 나타냄
                .and().logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점, 로그안웃성공 시 이동할 URL
                .and().oauth2Login() //OAuth2로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() //OAuth2로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록,
        //리소스 서버 즉, 소셜서비스들 에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 할 수 있다.
    }
}
