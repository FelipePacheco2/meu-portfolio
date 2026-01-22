package com.example.maintenanceHospital.servico.people;

import com.example.maintenanceHospital.mapperObject.people.EmployeeDTO;
import com.example.maintenanceHospital.mapperObject.people.EmployerMapper;
import com.example.maintenanceHospital.repository.people.EmployeeRepository;
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

    public EmployeeDTO create(EmployeeDTO dto){
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
