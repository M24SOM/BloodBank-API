package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donation;
import so.edu.uct.BloodBank.model.Records;

import java.util.List;

public interface RecordsRepository extends JpaRepository<Records,Long> {


    @Query(value = "SELECT sum(cc) FROM Records",nativeQuery = true)

    Long findByBloodType();

    @Query(value = "SELECT * FROM records b WHERE " +
            "b.receiptId LIKE CONCAT('%',:receiptId,'%')",nativeQuery = true)
    List<Records> findByReceipt(String receiptId);
}
