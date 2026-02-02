package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.Breeds;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
public record AnimalUpdateDTO(
        LocalDate birthDate,
        Breeds breed
) {
}
