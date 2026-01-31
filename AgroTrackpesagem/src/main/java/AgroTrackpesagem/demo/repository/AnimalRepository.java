package AgroTrackpesagem.demo.repository;

import AgroTrackpesagem.demo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("Select DISTINCT o From Animal o LEFT JOIN o.surrounded WHERE o.status = 'ACTIVE' ")
    List<Animal> findAllFull();

    @Query("Select o FROM Animal o LEFT JOIN o.surrounded where o.id in :id")
    Animal findByIdObject(@Param("id")Long id);
}
