package AgroTrackpesagem.demo.controller;

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

    @GetMapping
    public List<Weighing> listAll(){
        return service.listALl();
    }

}
