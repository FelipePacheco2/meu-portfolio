package AgroTrackpesagem.demo.mapperDto.surrounded;

import AgroTrackpesagem.demo.enums.PaddockType;
import AgroTrackpesagem.demo.mapperDto.MapperGeneric;
import AgroTrackpesagem.demo.model.Animal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SurroundedDTO{
    private Long id;
    private PaddockType type;
    private String name;
    private LocalDateTime createAt;
}
