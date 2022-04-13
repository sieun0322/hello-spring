package security.jwt.config;

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
import security.jwt.filter.MyFilter1;
import security.jwt.filter.MyFilter3;
import security.jwt.repository.MemberRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 사용X
                .and()
                .addFilter(corsFilter)//@CrossOrigin(인증x),시큐리티 필터에 등록 인증o
                .formLogin().disable()
                .httpBasic().disable()//http-authorization : ID,PW 암호화되지 않는다.(Basic 방식)=>https 사용. 토큰: 위험부담 낮춤(Bearer 방식, 유효시간 존재)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))//AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),memberRepository))//AuthenticationManager
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
