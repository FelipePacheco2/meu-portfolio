package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.model.Weighing;
import AgroTrackpesagem.demo.repository.WeighingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class WeighingService {
    private final WeighingRepository repository;

    public List<Weighing> listALl(){
        return repository.findAll();
    }

}
