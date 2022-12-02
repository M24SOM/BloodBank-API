package so.edu.uct.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "bloodType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Donor> donors;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "bloodType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Receipt> receipts;
}
