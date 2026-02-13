package AgroTrackpesagem.demo.repository;

import AgroTrackpesagem.demo.model.Surrounded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SurroundedRepository extends JpaRepository<Surrounded, Long> {


}
