package security.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import security.jwt.config.auth.PrincipalDetails;
import security.jwt.model.Member;
import security.jwt.repository.MemberRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
스프링 시큐리티에 BasicAuthenticationFilter 존재
권한이나 인증이 필요한 특정 주소를 요청했을 때 해당 필터 사용.
 아닐경우 사용X
*/
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,MemberRepository memberRepository) {
        super(authenticationManager);
        memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        System.out.println("authorization Filter!!");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader:"+jwtHeader);

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }

        /**정상 사용자 확인
         */
        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");
        String username
                = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString();

        if(username !=null){
            Member memberEntity = memberRepository.findByName(username);

            PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);

            //JWT 토큰 서명을 통해서 서명이 정상이면 확인된 사용자 이므로 강제로 생성
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails,null);
            //시큐리티 세션에 접근하여 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        }

    }
}
