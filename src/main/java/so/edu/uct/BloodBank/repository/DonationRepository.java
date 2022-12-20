package so.edu.uct.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.model.Donor;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Long> {
    @Query(value = "SELECT sum(cc) FROM Donation",nativeQuery = true)

    Long findByBloodType();


    @Query(value = "SELECT * FROM donation b WHERE " +
            "b.donorId LIKE CONCAT('%',:donorId,'%')",nativeQuery = true)
    List<Donation> findByDonor(String donorId);
}
