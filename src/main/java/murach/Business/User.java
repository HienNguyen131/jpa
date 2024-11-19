package murach.Business;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "userdb")// Ensure this matches the actual table name in your database
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generation of ID
    private Long ID;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    // Constructor without ID (for new users)
    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Default constructor (required by JPA)
    public User() {}

    // Getters and setters
    public Long getUserId() { return ID; }
    public void setUserId(Long id) { this.ID = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
