package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    List<Receipt> findByMobileNo(String mobileNo);

}
