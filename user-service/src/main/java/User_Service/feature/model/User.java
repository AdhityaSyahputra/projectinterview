package User_Service.feature.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USERSERVICE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
}
