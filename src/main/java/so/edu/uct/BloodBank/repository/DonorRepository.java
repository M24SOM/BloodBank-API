package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import so.edu.uct.BloodBank.model.Donor;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Long> {

    List<Donor> findByMobileNo(String mobileNo);

}
