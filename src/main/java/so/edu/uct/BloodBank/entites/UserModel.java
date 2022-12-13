package so.edu.uct.BloodBank.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private long id;
    private String name;
    private String username;
    private String password;
}