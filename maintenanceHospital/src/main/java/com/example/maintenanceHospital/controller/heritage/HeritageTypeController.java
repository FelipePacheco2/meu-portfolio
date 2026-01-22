package com.example.maintenanceHospital.controller.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.HeritageTypeDTO;
import com.example.maintenanceHospital.servico.heritage.HeritageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TipoPatrimonio")
public class HeritageTypeController {

    @Autowired
    HeritageTypeService service;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<HeritageTypeDTO> findAll(){
        return service.findAll();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public HeritageTypeDTO create(@RequestBody HeritageTypeDTO dto) throws InterruptedException {
        return service.create(dto);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public HeritageTypeDTO update(@RequestBody HeritageTypeDTO dto) { return service.update(dto);}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
