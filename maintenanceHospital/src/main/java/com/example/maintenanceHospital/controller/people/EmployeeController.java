package com.example.maintenanceHospital.controller.people;

import com.example.maintenanceHospital.mapperObject.people.EmployeeDTO;
import com.example.maintenanceHospital.servico.people.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll(){
        return service.findAll();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO create(@RequestBody EmployeeDTO dto){
        return service.create(dto);
    }

}
