package security.security1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 세션 삭제 1. 서버에서 삭제 2. 사용자 브라우저 종료 3. 유지 시간 초과
 * - 로그인 성공시 세션에 유저 정보까지 저장
 * - 세션 ID 에 따라 유저정보 전송
 *
 * 단점: 동시접속자 수 300 일경우, 서버 3개로 로그 밸런싱
 *      1. 최초 서버에만 연결
 *      2. 세션 전체 복제
 *      3. 한 데이터베이스에서 공유 (I/O. 하드디스크 오래 걸림. FULL SCAN)
 *      4. 메모리 서버 사용 (전기적 신호 RAM. 하드디스크보다 빠름) ex. REDIS
 *
 * 2장. TCP
 * - 통신 OSI 7계층 : 물리/데이터링크//네트워크/트렌스포트/세션/프리젠테이션/응용
 *              ex)전기선/공유기에서 해당 IP 찾음 ---LAN//IP/TCP.UDP 통신/인증체크/암호화.압축/롤 프로그램:궁 --WAN
 * - TCP : 데이터가 제대로 전송되었는지 ACK 로 확인. 신뢰성O. 느림
 * - UDP : 전화에서 사용. 신뢰성X. 빠름 ex) 반가워 ->반 워 사람은 이해할수 있다.
 *
 *3장. CIA
 * -C(기밀성):다른 나라에서 문서 획득X
 *          - 암호화: 열지 못함
 * -I(무결성):문서 변경X
 *          - 딱 붙어있어야 한다. 금고 전체 변경 불가
 * -A(가용성):위조된 문서가 전송X
 *
 * 문제1: 열쇠 전달의 문제
 * 문제2: 문서 누구로부터 왔는지
 *
 * 4장 RSA(암호화)
 * public key : 공개키
 * private key : 개인키
 *
 * 문제1: B의 공개키로 암호화 -> B가 B의 개인키로 연다.
 * 문제2: A의 개인키로 암호화 -> B가 A의 공개키로 열리면 A가 보냈구나
 *
 * 공개키 -> 개인키(암호화)
 * 개인키 -> 공개키(전자서명)
 *
 * B공개키 + A개인키 : A공개키로 열리면 B개인키로 열어본다. : 인증+암호화
 *
 **/
@SpringBootApplication(scanBasePackages = "security.security1")
public class SecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class,args);
    }

}
