package com.example.maintenanceHospital.servico.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageDTO;
import com.example.maintenanceHospital.mapperObject.heritage.HeritageMapper;
import com.example.maintenanceHospital.model.heritage.Heritage;
import com.example.maintenanceHospital.repository.heritage.HeritageRepository;
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


    public List<HeritageDTO> findAll(){
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional                                    //Problema no retorno, os dados estão sendo salvo no banco e a logica
    public HeritageDTO create(HeritageDTO dto){       //ja testei o findById, ele só não retorna null nos dados da Fk quando
        Heritage entity = mapper.toEntity(dto);       //pasamos direto, se não retorna só o id.
        return mapper.toDTO(repository.save(entity)); //bug de visualização A resolver....
    }

    @Transactional
    public HeritageDTO update(HeritageDTO dto){
        Heritage entity = mapper.toEntity(dto);
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
