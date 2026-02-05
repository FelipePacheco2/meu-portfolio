package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter  @Setter 
@Builder
public class AnimalResponseDTO {
        private Long id;
        private String tagIdentifier;
        private LocalDate birthDate;
        private SurroundedDTO surrounded;
        private Breeds breed;
        private AnimalStatus status;
}

