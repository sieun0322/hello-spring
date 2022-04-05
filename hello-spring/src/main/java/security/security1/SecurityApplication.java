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
 * 5장 RFC
 * http 벨연구소 WWW
 * 두 내부방 사이의 약속된 규칙 : RFC 1번 문서(방식을 프로토콜이라 함)...=>WWW(http프로토콜)
 *
 * JWT : RFC 7519번째 규칙을 활용.
 *
 * 6장 JWT(JSON Web Token)
 * : JSON 객체로 안전하게 전송하기 위한 표준
 * - HMAC 또는 RSA 또는 ECDSA
 * - 공개/개인 키 쌍을 사용하여 서명
 * - 서명된 토큰 중점. 무결성 확인
 *
 * 구조: 헤더(header).유효 탑재량(payload).서명(signature)
 *
 * 헤더: 토큰유형/서명알고리즘
 *      Base64Url 로 인코딩 (디코딩가능)
 * 유효 탑재량 : 클레임을 포함하는 페이로드
 *          * 클레임 : 엔티티(사용자), 추가 데이터에 대한 설명
 *     - 등록된 클레임 : 권장되는 미리 정의된 클레임 집합
 *          ex) iss(발행자) exp(만료시간) sub(주제) aud(청중)
 *     - 공개 소유권 주장
 *     - 개인 클레임 : 사용자 지정 클레임
 *     Base64Url 로 인코딩 (복호화가능)
 * 서명 : 인코딩된 헤더/페이로드/지정된 알고리즘을 가져와서 서명
 *       Base64Url 로 인코딩 (디코딩가능)
 *    - 개인키로 서명된 토큰은 발신자 확인 가능
 *  1) HMAC방식     + HMAC 시크릿키 포함
 *                 + SHA256 해쉬
 *                  => HS256
 *  2) RSA 방식
 *     :개인키로 암호화 -> 공개키로 서명 검증
 *
 * 웹브라우저의 로컬스토리지에 저장.
 * 서버에 정보요청시 JWT 함께 전송.
 *  1) HMAC 방식: 서버에서 검증(서버에서 알고 있는 시크릿키에 따라 만든 서명과 전송된 서명이 같은지 확인)후 정보 전송
 *  2) RSA 방식: 서버에서 공개키로 검증 후 정보 전송
 *
 * 결론 : 검증만 하면 되므로 세션을 사용하지 않는다.
 *       각 서버에서 시크릿 코드만 알고 있으면 검증 가능하다.
 *
 **/
@SpringBootApplication(scanBasePackages = "security.security1")
public class SecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class,args);
    }

}
