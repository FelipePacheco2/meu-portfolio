package com.example.maintenanceHospital.controller.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.LocationHospitalDTO;
import com.example.maintenanceHospital.servico.heritage.LocationHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localHospital")
public class LocationHospitalController {

    @Autowired
    LocationHospitalService service;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationHospitalDTO> findAll(){
        return service.findAll();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationHospitalDTO create(@RequestBody LocationHospitalDTO dto){
        return  service.create(dto);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public LocationHospitalDTO update(@RequestBody LocationHospitalDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
