package AgroTrackpesagem.demo.controller;

import AgroTrackpesagem.demo.assembler.surrounded.SurroundedAssembler;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.service.SurroundedService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/piquete")
@RequiredArgsConstructor
public class SurroundedController {
    private final SurroundedService service;
    private final SurroundedAssembler assembler;

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<SurroundedResponseDTO>>> listAll(){
        return ResponseEntity.ok(assembler.toCollectionModel(service.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SurroundedResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(service.findById(id)));
    }


}
