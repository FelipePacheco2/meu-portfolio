package com.example.maintenanceHospital.servico.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.CategoryHeritageDTO;
import com.example.maintenanceHospital.mapperObject.heritage.CategoryMapper;
import com.example.maintenanceHospital.model.heritage.CategoryHeritage;
import com.example.maintenanceHospital.repository.heritage.CategoryHeritageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryHeritageService {

    @Autowired
    CategoryHeritageRepository repository;
    @Autowired
    CategoryMapper mapper;

    public List<CategoryHeritageDTO> findAll(){
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional
    public CategoryHeritageDTO create(CategoryHeritageDTO dto){
        CategoryHeritage entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public CategoryHeritageDTO update(CategoryHeritageDTO dto){
        CategoryHeritage entity = findById(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public CategoryHeritage findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }
}
