package AgroTrackpesagem.demo.repository;

import AgroTrackpesagem.demo.model.Weighing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface WeighingRepository extends JpaRepository<Weighing, Long> {
    @Query(value = "SELECT * FROM weighing WHERE animal_id = :id ORDER BY weight_date DESC LIMIT 1", nativeQuery = true)
    Weighing findLatestWeighing(@Param("id") Long id);

    @Query("SELECT DISTINCT o FROM Weighing o WHERE o.animal.id = :id")
    List<Weighing> findAllRegisterWeighing(@Param("id") Long id);
}
