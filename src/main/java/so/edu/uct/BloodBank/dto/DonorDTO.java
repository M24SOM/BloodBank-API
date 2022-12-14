package so.edu.uct.BloodBank.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data @AllArgsConstructor
@NoArgsConstructor
public class DonorDTO {
    private Long id;
    private String name;
    private Date dateBirth;
    private String weight;
    private String mobileNo;

    private String bloodType;

    private String state;

    private Boolean isHealthy;


}
