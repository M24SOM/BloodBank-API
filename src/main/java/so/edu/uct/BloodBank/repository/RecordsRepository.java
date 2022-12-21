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


//    @Query(value="SELECT sum(cc) FROM records " +
//            "WHERE receiptId IN ( SELECT * FROM receipt b WHERE b.bloodTypeId LIKE CONCAT('%',:bloodType,'%')",nativeQuery = true)
    @Query(value = "SELECT sum(cc) FROM records " +
            "WHERE receiptId IN ( SELECT id FROM receipt b WHERE b.bloodTypeId =:bloodType);",nativeQuery = true)
    Long findByBloodType(String bloodType);
}
