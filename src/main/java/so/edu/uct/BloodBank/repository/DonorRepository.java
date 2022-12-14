package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Long> {

    List<Donor> findByMobileNo(String mobileNo);

    @Query(value = "SELECT * FROM donor b WHERE " +
            "b.StateId LIKE CONCAT('%',:query,'%')",nativeQuery = true)

    List<Donor> findByState(String query);
//    List<Donor> findByState(String state);

    @Query(value = "SELECT sum(id) FROM Donor",nativeQuery = true)

    Long sumOfDonor();

}
