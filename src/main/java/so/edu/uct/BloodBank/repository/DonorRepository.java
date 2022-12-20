package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.State;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Long> {

    List<Donor> findByMobileNo(String mobileNo);

    @Query(value = "SELECT * FROM donor b WHERE " +
            "b.bloodTypeId LIKE CONCAT('%',:bloodType,'%')",nativeQuery = true)

    List<Donor> findByBloodType(String bloodType);


    @Query(value = "SELECT * FROM donor b WHERE " +
            "b.StateId LIKE CONCAT('%',:StateId,'%')",nativeQuery = true)

    List<Donor> findByState(String StateId);


    @Query(value = "SELECT count(id) FROM Donor",nativeQuery = true)
    Long sumOfDonor();

}
