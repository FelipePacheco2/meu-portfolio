package com.example.maintenanceHospital.servico.order;

import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceMapper;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.repository.order.OccurrenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccurrenceService {
    @Autowired
    OccurrenceRepository repository;
    @Autowired
    OccurrenceMapper mapper;

    public List<OccurrenceDTO> findALl(){
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional
    public OccurrenceDTO create(OccurrenceDTO dto){
        Occurrence entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public OccurrenceDTO update(OccurrenceDTO dto){
        Occurrence entity = findById(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        repository.save(entity);
        return mapper.toDTO(findById(entity.getId()));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
    public Occurrence findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    public Occurrence findByID(Long id){
        return repository.findById(id)
                .orElseThrow();
    }
}
