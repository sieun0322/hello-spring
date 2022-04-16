package security.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.jwt.model.Member;
import security.jwt.repository.MemberRepository;

@RequiredArgsConstructor
@RestController
public class RestApiController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("home")
    public String home(){
        return "<h1>home</h1>";
    }
    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody Member member){
        System.out.println("!@#"+member.getName());
        System.out.println("!@#"+member.getPassword());

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");
        memberRepository.save(member);
        return "회원가입 완료";
    }
    @GetMapping("/api/v1/user")
    public String user(){
        System.out.println("유저");
        return "user";
    }
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
