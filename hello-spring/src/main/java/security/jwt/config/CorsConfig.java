package security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig{
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//내서버가 응답할때 json 자바스크립스에서 처리할수 있게 할지
        config.addAllowedOrigin("*");//모든 ip에 응답 허용
        config.addAllowedHeader("*");//모든 header에 응답 허용
        config.addAllowedMethod("*");//모든 get post 에 응답 허용
        source.registerCorsConfiguration("/api/**",config);
        return new CorsFilter(source);
    }

}
