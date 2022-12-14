package so.edu.uct.BloodBank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private long id;
    private String name;
    private String username;
    private String password;
}