package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.Donor;

public interface DonorRepository extends JpaRepository<Donor,Long> {
}
