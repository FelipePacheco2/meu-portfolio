package AgroTrackpesagem.demo.mapperDto.surrounded;

import AgroTrackpesagem.demo.enums.PaddockType;
import AgroTrackpesagem.demo.model.Animal;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SurroundedDTO{
    private PaddockType type;
    private String name;
    private Long maxCapacity;
}
