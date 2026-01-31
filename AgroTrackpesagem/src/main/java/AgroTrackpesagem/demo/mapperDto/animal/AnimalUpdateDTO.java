package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.Breeds;

import java.time.LocalDate;

public record AnimalUpdateDTO(
        LocalDate birthDate,
        Breeds breed
) {
}
