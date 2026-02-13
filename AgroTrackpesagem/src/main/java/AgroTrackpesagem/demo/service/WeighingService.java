package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.mapperDto.weighing.WeighingDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingMapper;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingResponseDTO;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Weighing;
import AgroTrackpesagem.demo.repository.WeighingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class WeighingService {
    private final WeighingRepository repository;
    private final AnimalService serviceAnimal;
    private final WeighingMapper mapper;

    public List<WeighingResponseDTO> FindAllByTag(String tag){
        Animal animal = serviceAnimal.isExistTag(tag);
        return mapper.toListResponse(repository.findAllRegisterWeighing(animal.getId()));
    }

    @Transactional
    public WeighingResponseDTO create(WeighingDTO dto){
        Animal animal = serviceAnimal.isExistTag(dto.tag());
        serviceAnimal.animalIsLive(animal);
        Weighing weighingLast = isExistRegister(animal.getId());

        Weighing weighing = new Weighing();
        weighing.setAnimal(animal);
        weighing.setWeight(dto.weight());

        if(weighingLast != null){
            double difference = differenceWeighing(dto.weight(), weighingLast.getWeight());
            weighing.setDailyWeightGain(difference);
        }else{
            weighing.setDailyWeightGain(0);
        }

        return mapper.toResponse(repository.save(weighing));
    }

    public Weighing isExistRegister(Long id){
        return repository.findLatestWeighing(id);
    }

    public double differenceWeighing(double weight, double lastWeight ){
        return weight - lastWeight;
    }

}
