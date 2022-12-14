package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    List<Receipt> findByMobileNo(String mobileNo);


    @Query(value = "SELECT sum(id) FROM Receipt",nativeQuery = true)

    Long sumOfReceipt();

}
