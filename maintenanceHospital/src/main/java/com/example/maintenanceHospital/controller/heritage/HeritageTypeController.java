package com.example.maintenanceHospital.controller.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageTypeDTO;
import com.example.maintenanceHospital.model.heritage.LocationHospital;
import com.example.maintenanceHospital.servico.heritage.HeritageTypeService;
import com.example.maintenanceHospital.servico.heritage.LocationHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoPatrimonio")
public class HeritageTypeController {

    @Autowired
    HeritageTypeService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HeritageTypeDTO> findAll(){
        return service.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HeritageTypeDTO create(@RequestBody HeritageTypeDTO dto) throws InterruptedException {
        return service.create(dto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public HeritageTypeDTO update(@RequestBody HeritageTypeDTO dto) { return service.update(dto);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
