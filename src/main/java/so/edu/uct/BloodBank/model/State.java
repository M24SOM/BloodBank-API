package so.edu.uct.BloodBank.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Hospital> hospitals;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Donor> donors;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Receipt> receipts;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
