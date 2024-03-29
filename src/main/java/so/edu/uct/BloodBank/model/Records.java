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
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cc;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="receiptId", referencedColumnName = "id", nullable = false)
    Receipt receipt;

    private Date date;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}

