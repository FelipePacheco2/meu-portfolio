package AgroTrackpesagem.demo.controller;

import AgroTrackpesagem.demo.assembler.surrounded.SurroundedAssembler;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.service.SurroundedService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piquetes")
@RequiredArgsConstructor
public class SurroundedController {
    private final SurroundedService service;
    private final SurroundedAssembler assembler;

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<EntityModel<SurroundedResponseDTO>>> listAll(){
        return ResponseEntity.ok(assembler.toCollectionModel(service.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SurroundedResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.findById(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<EntityModel<SurroundedResponseDTO>> create(@RequestBody SurroundedDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(service.create(dto)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<EntityModel<SurroundedResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody SurroundedDTO dto){
        return ResponseEntity.ok(assembler.toModel(service.update(dto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
