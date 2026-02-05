package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.SurroundedRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurroundedService {
    private final SurroundedRepository repository;
    private final SurroundedMapper mapper;

    public List<SurroundedResponseDTO> listAll(){

        List<SurroundedResponseDTO> SurroundedDTOs = new ArrayList<>();

        for(Surrounded surrounded : repository.findAll()){
            SurroundedResponseDTO surroundedDTO = mapper.toResponseDTO(surrounded);
            surroundedDTO.setCurrentCount(amountAnimal(surrounded));
            SurroundedDTOs.add(surroundedDTO);
        }

        return SurroundedDTOs;
    }

    public SurroundedResponseDTO findById(Long id){
        Surrounded surrounded = isExist(id);
        SurroundedResponseDTO surroundedR = mapper.toResponseDTO(surrounded);
        surroundedR.setCurrentCount(amountAnimal(surrounded));
        return surroundedR;
    }


    public Surrounded isExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("piquete não encontrado com ID: " + id));
    }

    public Surrounded fetchEmptySurround(Long id) {
        Surrounded surrounded = isExist(id);
        amountAnimal(surrounded);
        return surrounded;
    }

    public Long amountAnimal(Surrounded surrounded){
        Long totalAnimal = surrounded.getAnimals().stream()
                .filter(a -> a.getStatus() != AnimalStatus.DECEASED)
                .count();

        if (totalAnimal >= surrounded.getMaxCapacity()) {
            throw new RuntimeException("O piquete selecionado já atingiu a capacidade máxima");
        }

        return totalAnimal;
    }


}
