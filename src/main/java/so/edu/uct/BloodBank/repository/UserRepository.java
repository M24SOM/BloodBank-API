package so.edu.uct.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.User;
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
