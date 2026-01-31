package AgroTrackpesagem.demo.controller;


import AgroTrackpesagem.demo.assembler.animal.AnimalAssembler;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService service;
    private final AnimalAssembler assembler;

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<AnimalResponseDTO>>> listAll(){
        return ResponseEntity.ok(assembler.toCollectionModel(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AnimalResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.findById(id)));
    }

    @PostMapping()
    public AnimalDTO create(@RequestBody AnimalDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{idAnimal}/move/{idSurrounded}")
    public ResponseEntity<EntityModel<AnimalResponseDTO>> moveAnimal(
            @PathVariable Long idAnimal,
            @PathVariable Long idSurrounded){
        return ResponseEntity.ok(
                assembler.toModel(service.moveAnimal(idAnimal, idSurrounded))
        );
    }
}
