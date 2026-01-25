package com.example.maintenanceHospital.servico.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageDTO;
import com.example.maintenanceHospital.mapperObject.heritage.HeritageMapper;
import com.example.maintenanceHospital.model.heritage.Heritage;
import com.example.maintenanceHospital.repository.heritage.HeritageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeritageService {
    @Autowired
    HeritageRepository repository;
    @Autowired
    HeritageMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<HeritageDTO> findAll(){
        return mapper.toDTOList(repository.findAllFull());
    }

    @Transactional
    public HeritageDTO create(HeritageDTO dto){
        Heritage entitySave = repository.save(mapper.toEntity(dto));
        entityManager.clear(); // Limpa o cache para forçar o novo SELECT
        return mapper.toDTO(repository.findByIdObjet(entitySave.getId()));
    }

    @Transactional
    public HeritageDTO update(HeritageDTO dto){
        Heritage entity = findById(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(findById(entity.getId())));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }


   public Heritage findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

}
