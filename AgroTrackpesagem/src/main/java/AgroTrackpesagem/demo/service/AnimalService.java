package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.mapperDto.animal.AnimalDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalMapper;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return mapper.toResponseDTOList(repository.findAllFull());
    }

    public AnimalDTO create(AnimalDTO dto){
        Surrounded surrounded = surroundedService.isExist(dto.getSurrounded());
        Animal animal = mapper.toEntity(dto);
        animal.setSurrounded(surrounded);
        return mapper.toDTO(repository.save(animal));
    }

    @Transactional
    public AnimalResponseDTO moveAnimal(Long idAnimal, Long idSurrounded){
        if(!repository.existsById(idAnimal)){
            throw new RuntimeException("Animal não encotrado com o ID" + idAnimal);
        }

        Surrounded surrounded = surroundedService.isExist(idSurrounded);

        if(surrounded.getAnimals().size() > surrounded.getMaxCapacity()){
            throw  new RuntimeException("Piquete esta cheio");
        }

        Animal animal = repository.findByIdObject(idAnimal);
        animal.setSurrounded(surrounded);

        return mapper.toResponseDTO(repository.save(animal));
    }

    public AnimalResponseDTO findById(Long id){
        return mapper.toResponseDTO(repository.findByIdObject(id));
    }

}
