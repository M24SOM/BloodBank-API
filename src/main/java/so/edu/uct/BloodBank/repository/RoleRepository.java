package so.edu.uct.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
