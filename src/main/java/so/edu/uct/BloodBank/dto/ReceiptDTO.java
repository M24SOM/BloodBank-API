package so.edu.uct.BloodBank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private Long id;
    private String name;
    private Date dateBirth;
    private String mobileNo;
    private String bloodType;
    private String state;
    private String hospital;

    private String createdAt;
    private String updatedAt;

}
