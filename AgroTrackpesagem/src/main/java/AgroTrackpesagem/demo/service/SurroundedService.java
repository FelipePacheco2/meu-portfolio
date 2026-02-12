package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.SurroundedRepository;
import jakarta.persistence.Entity;
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
        return setAmountAnimal(surrounded);
    }

    public SurroundedResponseDTO create(SurroundedDTO dto){
        Surrounded surrounded = mapper.toEntity(dto);
        surrounded.setMaxCapacity(dto.getMaxCapacity());
        return setAmountAnimal(repository.save(surrounded));
    }

    public SurroundedResponseDTO update(SurroundedDTO dto, Long id){
        Surrounded entity = isExist(id);
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toResponseDTO(repository.save(entity));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public SurroundedResponseDTO setAmountAnimal(Surrounded surrounded){
        SurroundedResponseDTO surroundedR = mapper.toResponseDTO(surrounded);
        surroundedR.setCurrentCount(amountAnimal(surrounded));

        return surroundedR;
    }

    // verifica se existe
    public Surrounded isExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("piquete não encontrado com ID: " + id));
    }

    // verifica se existe e se esta vivo o animal
    public Surrounded fetchEmptySurround(Long id) {
        Surrounded surrounded = isExist(id);
        amountAnimal(surrounded);
        return surrounded;
    }

    // retorna quantidade dentro do piquete (não considera animais mortos)
    public Long amountAnimal(Surrounded surrounded){

        if(surrounded.getAnimals() != null) {
            Long totalAnimal = surrounded.getAnimals().stream()
                    .filter(a -> a.getStatus() != AnimalStatus.DECEASED)
                    .count();

            if (totalAnimal >= surrounded.getMaxCapacity()) {
                throw new RuntimeException("O piquete selecionado já atingiu a capacidade máxima");
            }

            return totalAnimal;
        }

        return 0L;
    }


}
