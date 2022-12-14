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
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cc;

    private String type;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="bloodTypeId", referencedColumnName = "id")
    BloodType bloodType;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="donorId", referencedColumnName = "id")
    Donor donor;


    private Date date;
}

