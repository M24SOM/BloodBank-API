package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
