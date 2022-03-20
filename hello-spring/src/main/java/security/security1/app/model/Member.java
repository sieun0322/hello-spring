package security.security1.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String password;
    private String email;
    private String role;

    private String provider;
    private String providerId;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Member(String name, String password, String email, String role, String provider, String providerId, Timestamp createDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }
}
