package security.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.jwt.config.auth.PrincipalDetails;
import security.jwt.model.Member;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
스프링 시큐리티에 UsernamePasswordAuthenticationFilter 존재

*/
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인 시도");

        try {
            /*BufferedReader br = request.getReader();
            String input = null;
            while((input = br.readLine())!= null){
                System.out.println(input);
            }*/
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            System.out.println(member);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getName(),member.getPassword());
            //PrincipalDetailsService 의 loadUserByUserName() 함수가 실행됨
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);
            //authentication 객체가 session 영역에 저장됨(권한관리). 로그인 성공
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getMember().getName());
            System.out.println(request.getInputStream().toString());
            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //attemptAuthentication 실행후 인증이 정상적이면 실행.
    //요창자에게 JWT토큰 response
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //Hash 암호방식.RSA X
        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+(JwtProperties.EXPIRATION_TIME))) //10분
                .withClaim("id",principalDetails.getMember().getId())
                .withClaim("username",principalDetails.getMember().getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING,JwtProperties.TOKEN_PREFIX+jwtToken);
        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
