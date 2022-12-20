package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;
import so.edu.uct.BloodBank.model.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {

//    @Query(value = "SELECT * FROM receipt b WHERE " +
//            "b.mobileNo LIKE CONCAT('%',:mobileNo,'%')",nativeQuery = true)
    List<Receipt> findByMobileNo(String mobileNo);

    @Query(value = "SELECT * FROM receipt b WHERE " +
            "b.bloodTypeId LIKE CONCAT('%',:bloodType,'%')",nativeQuery = true)

    List<Receipt> findByBloodType(String bloodType);

    @Query(value = "SELECT * FROM receipt b WHERE " +
            "b.StateId LIKE CONCAT('%',:StateId,'%')",nativeQuery = true)

    List<Receipt> findByState(String StateId);
    @Query(value = "SELECT count(id) FROM Receipt",nativeQuery = true)

    Long sumOfReceipt();

    @Query(value = "SELECT * FROM receipt b WHERE " +
            "b.HospitalId LIKE CONCAT('%',:HospitalId,'%')",nativeQuery = true)

    List<Receipt> findByHospital(String HospitalId);

}
