package security.security1.config.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import security.security1.app.model.Member;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Map;

/**
 * Security ContextHolder
 * : Authentication 타입 객체 필요
 *   Authentication 안에 User 정보
 *   User 오브젝트 타입은 UserDetails 타입 객체
 */
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayDeque<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //마지막 로그인 1년 지나면 false
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
