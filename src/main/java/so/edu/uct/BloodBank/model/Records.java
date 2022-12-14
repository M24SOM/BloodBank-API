package so.edu.uct.BloodBank.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonBackReference;
import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Records {
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
    @JoinColumn(name="receiptId", referencedColumnName = "id")
    Receipt receipt;

    @Column(name="created_at")
    private Date createdAt;
}

