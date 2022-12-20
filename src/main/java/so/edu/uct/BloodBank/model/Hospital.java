package so.edu.uct.BloodBank.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.List;


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
    @JoinColumn(name="StateId", referencedColumnName = "id")
    State state;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Receipt> receipts;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
