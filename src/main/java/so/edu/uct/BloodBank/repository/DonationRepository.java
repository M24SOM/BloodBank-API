package so.edu.uct.BloodBank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donation;

public interface DonationRepository extends JpaRepository<Donation,Long> {
    @Query(value = "SELECT sum(cc) FROM Donation",nativeQuery = true)

    Long findByBloodType();
}
