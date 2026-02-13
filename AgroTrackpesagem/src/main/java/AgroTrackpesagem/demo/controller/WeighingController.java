package AgroTrackpesagem.demo.controller;

import AgroTrackpesagem.demo.assembler.WeighingAssembler;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingResponseDTO;
import AgroTrackpesagem.demo.service.WeighingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Weighing")
@RequiredArgsConstructor
@Tag(name = "Weighing",
        description = "Registro de pesagens, monitoramento de ganho de peso e histórico biométrico dos animais.")
public class WeighingController {
    private final WeighingService service;
    private final WeighingAssembler assembler;

    @GetMapping("/search")
    @Operation(
            summary = "Consultar registros biométrico via identificador único (Tag)",
            description = "Retorna os dados detalhados de um animal " +
                    "específico utilizando o código da tag/brinco como critério de busca."
    )
    public ResponseEntity<CollectionModel<EntityModel<WeighingResponseDTO>>> listAll(@RequestParam("tag") String tag ){
        return ResponseEntity.ok(assembler.toCollectionModel(service.FindAllByTag(tag)));
    }

    @PostMapping("/register")
    public ResponseEntity<EntityModel<WeighingResponseDTO>> create(@RequestBody WeighingDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(service.create(dto)));
    }

}
