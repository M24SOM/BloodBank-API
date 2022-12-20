package so.edu.uct.BloodBank.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date dateBirth;
    private String mobileNo;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="bloodTypeId", referencedColumnName = "id")
    BloodType bloodType;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="StateId", referencedColumnName = "id")
    State state;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="HospitalId", referencedColumnName = "id")
    Hospital hospital;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
