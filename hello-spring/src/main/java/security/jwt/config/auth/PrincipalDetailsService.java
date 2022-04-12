package security.jwt.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.jwt.repository.MemberRepository;
import security.jwt.model.Member;

//http://localhost:8080/login SeurityConfig 자동 설정
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private  final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByName(username);
        return new PrincipalDetails(memberEntity);
    }
}
