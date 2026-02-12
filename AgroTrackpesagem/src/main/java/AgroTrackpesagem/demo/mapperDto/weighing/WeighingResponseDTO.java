package AgroTrackpesagem.demo.mapperDto.weighing;

import AgroTrackpesagem.demo.mapperDto.animal.AnimalWeighigDTO;
import AgroTrackpesagem.demo.model.Animal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class WeighingResponseDTO {
    private Long id;
    private double dailyWeightGain;
    private double weight;
    private LocalDate weightDate;
    private AnimalWeighigDTO animal;
}
