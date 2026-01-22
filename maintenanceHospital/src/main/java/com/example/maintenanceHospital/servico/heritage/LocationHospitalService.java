package com.example.maintenanceHospital.servico.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.LocationHospitalDTO;
import com.example.maintenanceHospital.mapperObject.heritage.LocationHospitalMapper;
import com.example.maintenanceHospital.model.heritage.LocationHospital;
import com.example.maintenanceHospital.repository.heritage.LocationHospitalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationHospitalService {

    @Autowired
    LocationHospitalRepository repository;
    @Autowired
    LocationHospitalMapper mapper;


    public List<LocationHospitalDTO> findAll(){
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional
    public LocationHospitalDTO create(LocationHospitalDTO dto){
        LocationHospital entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public LocationHospitalDTO update(LocationHospitalDTO dto){

        LocationHospital entity = findByID(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public LocationHospital findByID(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

}
