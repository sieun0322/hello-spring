package msa.discoveryservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import security.jwt.config.jwt.JwtAuthenticationFilter;
import security.jwt.config.jwt.JwtAuthorizationFilter;
import security.jwt.filter.MyFilter3;
import security.jwt.repository.MemberRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable()
             .httpBasic().disable()//http-authorization : ID,PW 암호화되지 않는다.(Basic 방식)=>https 사용. 토큰: 위험부담 낮춤(Bearer 방식, 유효시간 존재)
        ;
    }
}
