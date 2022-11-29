package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.BloodType;

public interface BloodTypeRepository extends JpaRepository<BloodType,Long> {
}
