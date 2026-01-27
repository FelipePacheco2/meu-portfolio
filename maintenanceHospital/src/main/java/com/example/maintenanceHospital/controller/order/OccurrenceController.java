package com.example.maintenanceHospital.controller.order;

import com.example.maintenanceHospital.assembler.order.OccurrenceAssembler;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.servico.order.OccurrenceService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocorrencia")
public class OccurrenceController {

    private final OccurrenceService service;
    private final OccurrenceAssembler occurrenceAssembler;

    public OccurrenceController(OccurrenceService service, OccurrenceAssembler assembler) {
        this.service = service;
        this.occurrenceAssembler = assembler;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<OccurrenceDTO>>> findAll(){
        return ResponseEntity.ok(occurrenceAssembler.toCollectionModel(service.findALl()));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<EntityModel<OccurrenceDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(occurrenceAssembler.toModel(service.findById(id)));
    }

    @PostMapping()
    public ResponseEntity<EntityModel<OccurrenceDTO>>  create(@RequestBody OccurrenceDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(occurrenceAssembler.toModel(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OccurrenceDTO>> update(@PathVariable Long id, @RequestBody OccurrenceDTO dto){
        return ResponseEntity.ok(occurrenceAssembler.toModel(service.update(id, dto)));
    }
}
