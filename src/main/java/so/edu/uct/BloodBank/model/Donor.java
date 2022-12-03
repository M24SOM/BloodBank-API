package so.edu.uct.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date dateBirth;
    private String weight;
    private String mobileNo;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="bloodTypeId", referencedColumnName = "id")
    BloodType bloodType;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="StateId", referencedColumnName = "id")
    State state;
    private Boolean isHealthy;
}
