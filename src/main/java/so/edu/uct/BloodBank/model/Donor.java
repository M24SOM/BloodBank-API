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
    private Date date_birth;
    private String weight;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="bloodTypeId", referencedColumnName = "id")
    BloodType bloodType;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="State_Id", referencedColumnName = "id")
    State state;
    private Boolean is_healthy;
}
