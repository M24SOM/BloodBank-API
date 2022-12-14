package so.edu.uct.BloodBank.model;
import lombok.*;
import jakarta.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hospital{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="state_Id", referencedColumnName = "id")
    State state;

    @Column(name="created_at")
    private Date createdAt;
}
