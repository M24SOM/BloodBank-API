package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Hospital;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    @Query(value = "SELECT * FROM hospital b WHERE " +
            "b.StateId LIKE CONCAT('%',:StateId,'%')",nativeQuery = true)
    List<Hospital> findByState(String StateId);

    @Query(value = "SELECT count(id) FROM Hospital",nativeQuery = true)

    Long sumOfHospital();
}
