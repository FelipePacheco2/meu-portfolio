package AgroTrackpesagem.demo.mapperDto.surrounded;

import AgroTrackpesagem.demo.enums.PaddockType;
import AgroTrackpesagem.demo.model.Animal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SurroundedResponseDTO {
    private Long id;
    private PaddockType type;
    private String name;
    private LocalDateTime createAt;
    private Long maxCapacity;
    private Long CurrentCount;
}
