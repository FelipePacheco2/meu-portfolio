package com.example.maintenanceHospital.servico.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageTypeDTO;
import com.example.maintenanceHospital.mapperObject.heritage.HeritageTypeMapper;
import com.example.maintenanceHospital.model.heritage.HeritageType;
import com.example.maintenanceHospital.repository.heritage.HeritageTypeRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeritageTypeService {
    @Autowired
    HeritageTypeRepository repository;
    @Autowired
    HeritageTypeMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<HeritageTypeDTO> findAll(){
        return mapper.toDTOList(repository.findAllFull());
    }

    @Transactional
    public HeritageTypeDTO create(HeritageTypeDTO dto){
        HeritageType entitySave = repository.save(mapper.toEntity(dto));
        entityManager.clear();
        return mapper.toDTO(repository.findByIdObject(entitySave.getId()));
    }

    @Transactional
    public HeritageTypeDTO update(HeritageTypeDTO dto){
        HeritageType entity = findByID(dto.id());
        mapper.updateEntityFromDTO(dto, entity);

        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public HeritageType findByID(Long id){
        return repository.findById(id)
                .orElseThrow();
    }



}
