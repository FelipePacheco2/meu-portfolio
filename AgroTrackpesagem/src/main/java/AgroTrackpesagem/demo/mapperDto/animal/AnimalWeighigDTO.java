package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;

import java.time.LocalDate;

public record AnimalWeighigDTO(
         String tagIdentifier,
         SurroundedDTO surrounded,
         Breeds breed,
         AnimalStatus status
) {
}
