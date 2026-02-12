package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.mapperDto.animal.*;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
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
        Surrounded surrounded = fetchEmptySurround(dto.getSurrounded());
        Animal animal = mapper.toEntity(dto);
        animalIsLive(animal);

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
        Animal entity = getActiveById(idAnimal);
        Surrounded surrounded = fetchEmptySurround(dto.surrounded());
        entity.setSurrounded(surrounded);

        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public AnimalResponseDTO updateStatus(Long idAnimal, UpdateAnimalStatusDTO dto){
        Animal entity = isExist(idAnimal);
        animalIsLive(entity);
        entity.setStatus(dto.status());

        return mapper.toResponseDTO(repository.save(entity));
    }

    //confere se o cercado esta chei0
    public Surrounded fetchEmptySurround(Long id) {
        Surrounded surrounded = surroundedService.isExist(id);

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

    public Animal isExistTag(String tag){
        return repository.findByTag(tag)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com a tag" + tag));
    }

    // retorna só animais vivos
    public Animal getActiveById(Long id) {
        Animal entity = isExist(id);
        animalIsLive(entity);

        return entity;
    }

    public void animalIsLive(Animal animal){
        if (animal.getStatus() == AnimalStatus.DECEASED) {
            throw new RuntimeException("Não é possível cadastrar, alocar ou alterar o status de animais falecidos.");
        }
    }

    public void delete(Long idAnimal){
        repository.deleteById(idAnimal);
    }


}
