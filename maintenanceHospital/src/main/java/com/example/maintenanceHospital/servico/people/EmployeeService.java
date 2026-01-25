package com.example.maintenanceHospital.servico.people;

import com.example.maintenanceHospital.mapperObject.people.EmployeeDTO;
import com.example.maintenanceHospital.mapperObject.people.EmployerMapper;
import com.example.maintenanceHospital.model.people.Employee;
import com.example.maintenanceHospital.repository.people.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    EmployerMapper mapper;

    public List<EmployeeDTO> findAll(){
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional
    public EmployeeDTO create(EmployeeDTO dto){
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public EmployeeDTO update(EmployeeDTO dto){
        Employee entity = findById(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(long id){
        repository.deleteById(id);
    }

    public Employee findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }
}
