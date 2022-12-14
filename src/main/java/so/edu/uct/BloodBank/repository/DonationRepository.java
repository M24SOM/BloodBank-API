package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import so.edu.uct.BloodBank.model.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Long> {
//    @Query(value = "SELECT * FROM donation d WHERE d.bloodType == :query ",
//            nativeQuery = true)
//    int findByBloodType(Integer bloodTypeId);

//    @Query(value = "SELECT * FROM donation b WHERE " +
//            "b.bloodTypeId = :query )",nativeQuery = true)

    @Query(value = "SELECT sum(cc) FROM Donation",nativeQuery = true)

    Long findByBloodType();
}
