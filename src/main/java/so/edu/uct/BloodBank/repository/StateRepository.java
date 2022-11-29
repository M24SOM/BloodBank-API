package so.edu.uct.BloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.edu.uct.BloodBank.model.State;

public interface StateRepository extends JpaRepository<State,Long> {
}
