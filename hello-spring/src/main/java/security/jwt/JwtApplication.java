package security.jwt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "security.jwt")
public class JwtApplication {
    public static void main(String[] args){
        SpringApplication.run(JwtApplication.class,args);
    }

}
