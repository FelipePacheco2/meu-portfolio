package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.mapperDto.animal.*;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository repository;
    private final SurroundedService surroundedService;
    private final AnimalMapper mapper;

    public List<AnimalResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public AnimalResponseDTO findById(Long idAnimal){
        return mapper.toResponseDTO(isExist(idAnimal));
    }

    @Transactional
    public AnimalResponseDTO create(AnimalDTO dto){
        Surrounded surrounded = validateEntryConditions(dto);
        Animal animal = mapper.toEntity(dto);
        animal.setSurrounded(surrounded);
        return mapper.toResponseDTO(repository.save(animal));
    }

    @Transactional
    public AnimalResponseDTO update(Long idAnimal, AnimalUpdateDTO dto){
        Animal entity = isExist(idAnimal);
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public AnimalResponseDTO moveAnimal(Long idAnimal, AnimalSurroundedMoveDTO dto) {
        isExist(idAnimal);
        Surrounded surrounded = surroundedService.isExist(dto.surrounded());

        Animal entity = repository.findByIdObject(idAnimal);
        entity.setSurrounded(surrounded);

        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public AnimalResponseDTO updateStatus(Long idAnimal, UpdateAnimalStatusDTO dto){
        Animal entity = isExist(idAnimal);

        if (entity.getStatus() == AnimalStatus.DECEASED) {
            throw new RuntimeException("Não é possível alterar o status de um animal que já consta como morto.");
        }

        if(!entity.getStatus().canTransitionTo(dto.status())){
            throw new RuntimeException("Transição de status inválida");
        }

        entity.setStatus(dto.status());

        return mapper.toResponseDTO(repository.save(entity));
    }

    public Surrounded validateEntryConditions(AnimalDTO dto) {

        Surrounded surrounded = surroundedService.isExist(dto.getSurrounded());

        if (dto.getStatus() == AnimalStatus.DECEASED) {
            throw new RuntimeException(
                    "Não é possivel cadastrar animals falecidos");
        }

        long totalAnimal = surrounded.getAnimals().stream()
                .filter(a -> a.getStatus() != AnimalStatus.DECEASED)
                .count();

        if (totalAnimal >= surrounded.getMaxCapacity()) {
            throw new RuntimeException("O piquete selecionado já atingiu a capacidade máxima");
        }

        return surrounded;
    }

    public Animal isExist(Long idAnimal){
        return repository.findById(idAnimal)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID " + idAnimal));
    }


}
