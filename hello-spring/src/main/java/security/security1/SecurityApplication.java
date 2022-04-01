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
 */
@SpringBootApplication(scanBasePackages = "security.security1")
public class SecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class,args);
    }

}
