package com.example.maintenanceHospital.controller.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageDTO;
import com.example.maintenanceHospital.servico.heritage.HeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrimonio")
public class HeritageController {

    @Autowired
    HeritageService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HeritageDTO> findAll(){
        return service.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HeritageDTO create(@RequestBody HeritageDTO dto){
        return service.create(dto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HeritageDTO update(@RequestBody HeritageDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
