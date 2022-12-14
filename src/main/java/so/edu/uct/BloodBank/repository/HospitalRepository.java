package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    @Query(value = "SELECT count(id) FROM Hospital",nativeQuery = true)

    Long sumOfHospital();
}
