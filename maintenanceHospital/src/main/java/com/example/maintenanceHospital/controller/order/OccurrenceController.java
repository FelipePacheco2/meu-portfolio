package com.example.maintenanceHospital.controller.order;

import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.servico.order.OccurrenceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/occurrence")
public class OccurrenceController {
    @Autowired
    OccurrenceService service;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<OccurrenceDTO> findAll(){
        return service.findALl();
    }

    @Transactional
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceDTO create(@RequestBody OccurrenceDTO dto){
        return service.create(dto);
    }

    @Transactional
    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public OccurrenceDTO update(@RequestBody OccurrenceDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
