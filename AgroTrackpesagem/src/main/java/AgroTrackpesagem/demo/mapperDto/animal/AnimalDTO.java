package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.model.Weighing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AnimalDTO {
    private Long id;
    private String tagIdentifier;
    private LocalDate birthDate;
    private Breeds breed;
    private Long surrounded;
    private List<Weighing> weights = new ArrayList<>();
    private AnimalStatus status;
}
