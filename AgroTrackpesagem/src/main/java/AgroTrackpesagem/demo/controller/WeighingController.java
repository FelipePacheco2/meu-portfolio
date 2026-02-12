package AgroTrackpesagem.demo.controller;

import AgroTrackpesagem.demo.assembler.WeighingAssembler;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingResponseDTO;
import AgroTrackpesagem.demo.model.Weighing;
import AgroTrackpesagem.demo.service.WeighingService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pesagens")
@RequiredArgsConstructor
public class WeighingController {
    private final WeighingService service;
    private final WeighingAssembler assembler;

    @GetMapping("/search")
    public ResponseEntity<CollectionModel<EntityModel<WeighingResponseDTO>>> listAll(@RequestParam("tag") String tag ){
        return ResponseEntity.ok(assembler.toCollectionModel(service.FindAllByTag(tag)));
    }

    @PostMapping("/register")
    public ResponseEntity<EntityModel<WeighingResponseDTO>> create(@RequestBody WeighingDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(service.create(dto)));
    }

}
