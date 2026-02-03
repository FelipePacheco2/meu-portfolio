package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import lombok.Builder;

@Builder
public record AnimalSurroundedMoveDTO(
        Long surrounded
) {
}
