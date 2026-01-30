package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;

@Getter  @Setter
public class AnimalResponseDTO {
        private Long id;
        private String tagIdentifier;
        private LocalDate birthDate;
        @JsonIgnore
        private Object surrounded;
        private AnimalStatus status;

        // O Assembler transforma o de cima neste aqui para o JSON final
        // Use @JsonProperty para que no JSON apareça apenas como "surrounded"
        @JsonProperty("surrounded")
        public Object getSurroundedResponse() {
                return this.surrounded;
        }
}

