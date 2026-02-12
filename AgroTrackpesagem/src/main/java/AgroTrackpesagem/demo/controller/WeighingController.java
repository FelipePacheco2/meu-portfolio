package AgroTrackpesagem.demo.controller;

import AgroTrackpesagem.demo.mapperDto.weighing.WeighingDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingResponseDTO;
import AgroTrackpesagem.demo.model.Weighing;
import AgroTrackpesagem.demo.service.WeighingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pesagens")
@RequiredArgsConstructor
public class WeighingController {
    private final WeighingService service;

    @GetMapping("/search")
    public List<WeighingResponseDTO> listAll(@RequestParam("tag") String tag ){
        return service.FindAllByTag(tag);
    }

    @PostMapping
    public WeighingResponseDTO create(@RequestBody WeighingDTO dto){
        return service.create(dto);
    }

}
