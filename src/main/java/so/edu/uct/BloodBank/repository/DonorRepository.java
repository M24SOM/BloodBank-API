package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.State;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Long> {

    List<Donor> findByMobileNo(String mobileNo);

//    @Query(value = "select * from Donor where state_id = 2",nativeQuery = true)

    @Query(value = "select * from Donor d where d.StateId = :state", nativeQuery = true)
//    List<Donor> findByStateId(@Param("state_id")String query);

    List<Donor> findByState(Long state);


    @Query(value = "SELECT count(id) FROM Donor",nativeQuery = true)
    Long sumOfDonor();

}
