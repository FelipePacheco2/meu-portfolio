package AgroTrackpesagem.demo.repository;

import AgroTrackpesagem.demo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT DISTINCT o FROM Animal o LEFT JOIN o.surrounded WHERE o.status = 'ACTIVE' ")
    List<Animal> findAllFull();

    @Query("SELECT o FROM Animal o LEFT JOIN o.surrounded WHERE o.id in :id")
    Animal findByIdObject(@Param("id")Long id);

    @Query("SELECT o FROM Animal o WHERE o.tagIdentifier = :tag")
    Optional<Animal> findByTag(@Param("tag")String tag);
}