package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import AgroTrackpesagem.demo.enums.PaddockType;
import AgroTrackpesagem.demo.mapperDto.animal.*;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.AnimalRepository;
import AgroTrackpesagem.demo.repository.SurroundedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AnimalServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AnimalMapper mapper;
    @Mock
    private AnimalRepository animalRepository;// estou criando um duble
    @Mock
    private SurroundedRepository surroundedRepository;
    @InjectMocks
    private AnimalService animalService;//  injeta o duble aqui dentro
    @Mock
    private SurroundedService surroundedService;


    private Animal createAnimal(Surrounded surrounded, AnimalStatus status){
        LocalDate date = LocalDate.of(2024, 1,30 );
        return Animal.builder()
                .id(1L)
                .tagIdentifier("A1")
                .birthDate(date)
                .breed(Breeds.NELORE)
                .surrounded(surrounded)
                .status(status)
                .build();
    }

    private AnimalDTO createAnimalDTO(AnimalStatus status){
        LocalDate date = LocalDate.of(2024, 1,30 );
        return AnimalDTO.builder()
                .id(1L)
                .tagIdentifier("A1")
                .birthDate(date)
                .breed(Breeds.ANGUS)
                .surrounded(1L)
                /*.weights()*/
                .status(status)
                .build();
    }

    private Surrounded createSurrounded(Long limit, int busy){
        LocalDateTime dateTime = LocalDateTime.of(2026, 2, 1, 14, 30);
        List<Animal> existingAnimals = new ArrayList<>();

        for(int i = 0; i < busy; i++){
            existingAnimals.add(new Animal());
        }

        return Surrounded.builder()
                .id(1L)
                .type(PaddockType.FATTENING)
                .name("A1")
                .maxCapacity(limit)
                .createAt(dateTime)
                .animals(existingAnimals)
                .build();
    }


    @Test
    @DisplayName("Sucesso: Animals listados")
    void findAll() {
        List<Animal> animals = List.of(new Animal(), new Animal());
        AnimalResponseDTO Animal = AnimalResponseDTO.builder().build();
        List<AnimalResponseDTO> animalsResponse = List.of(Animal, Animal);

        Mockito.when(animalRepository.findAll()).thenReturn(animals);
        Mockito.when(mapper.toResponseDTOList(animals)).thenReturn(animalsResponse);

        List<AnimalResponseDTO> result = animalService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(animalRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Sucesso: Animal encontrado")
    void findById() {
        AnimalResponseDTO responseMock = AnimalResponseDTO.builder().build();
        responseMock.setId(1L);
        responseMock.setTagIdentifier("A1");

        Mockito.when(animalRepository.findById(responseMock.getId())).thenReturn(Optional.of(new Animal()));
        Mockito.when(mapper.toResponseDTO(new Animal())).thenReturn(responseMock);

        AnimalResponseDTO result = animalService.findById(1L);

        assertNotNull(result);
        assertEquals("A1", result.getTagIdentifier()); // verifica se é o mesmo objeto
        Mockito.verify(animalRepository, Mockito.times(1)).findById(responseMock.getId());
    }

    @Test
    @DisplayName("Validação: Impedir registro de animal morto")
    void preventRegistrationWhenStatusIsDeceased(){
        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.DECEASED);
        Surrounded surrounded = createSurrounded(2L, 1);
        Animal animal = Animal.builder().status(AnimalStatus.DECEASED).build();

        Mockito.when(surroundedService.fetchEmptySurround(1L)).thenReturn(surrounded);
        Mockito.when(mapper.toEntity(animalDTO)).thenReturn(animal);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            animalService.create(animalDTO);
        });

        System.out.print("Mensagem: "+ exception.getMessage() + "\n");

        assertEquals("Não é possível cadastrar, alocar ou alterar o status de animais falecidos.", exception.getMessage());
        Mockito.verify(animalRepository, never()).save(any());
    }

    @Test
    @DisplayName("Validação: Impedir registro em cercados cheios")
    void preventRegistrationWhenTheEnclosureIsFull(){
        Surrounded surroundedFull = createSurrounded(1L, 1);
        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.ACTIVE);
        Mockito.when(surroundedService.fetchEmptySurround(any())).thenReturn(surroundedFull);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            animalService.create(animalDTO);
        });

        System.out.print("Mensagem: "+ exception.getMessage()+ "\n");
        assertEquals("O piquete selecionado já atingiu a capacidade máxima", exception.getMessage());
        Mockito.verify(animalRepository, never()).save(any());
    }

    @Test
    @DisplayName("Sucesso: Animal registrado")
    void create() {
        Surrounded surrounded = createSurrounded(2L, 0);
        Animal animal = new Animal();
        AnimalResponseDTO Animaldto = AnimalResponseDTO.builder().build();

        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.ACTIVE);
        animalDTO.setSurrounded(surrounded.getId());

        Mockito.when(surroundedService.fetchEmptySurround(surrounded.getId())).thenReturn(surrounded);

        Mockito.when(mapper.toEntity(animalDTO)).thenReturn(animal);
        Mockito.when(mapper.toResponseDTO(any())).thenReturn(Animaldto);
        Mockito.when(animalRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        AnimalResponseDTO result = animalService.create(animalDTO);

        assertNotNull(result);
        Mockito.verify(animalRepository).save(any());
    }

    @Test
    @DisplayName("Sucesso: Animal Atualizado")
    void update() {
        LocalDate date = LocalDate.of(2025, 1,30 );
        AnimalUpdateDTO animalUp = AnimalUpdateDTO.builder()
                .breed(Breeds.ANGUS)
                .birthDate(date)
                .build();
        Animal animal = createAnimal(createSurrounded(1L, 1), AnimalStatus.ACTIVE);
        Mockito.when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        doAnswer(invocation -> {
            AnimalUpdateDTO d = invocation.getArgument(0);
            Animal e = invocation.getArgument(1);
            e.setBreed(d.breed());
            e.setBirthDate(d.birthDate());
            return null;
        }).when(mapper).updateEntityFromDTO(any(AnimalUpdateDTO.class), any(Animal.class));

        Mockito.when(animalRepository.save(any(Animal.class))).thenAnswer(i -> i.getArguments()[0]);

        Mockito.when(mapper.toResponseDTO(any(Animal.class))).thenAnswer(i -> {
            Animal e = i.getArgument(0);
            return AnimalResponseDTO.builder().breed(e.getBreed()).birthDate(e.getBirthDate()).build();
        });


        AnimalResponseDTO result = animalService.update(1L, animalUp);

        assertNotNull(result);
        assertEquals(Breeds.ANGUS, result.getBreed());
        assertEquals(date, result.getBirthDate());
        Mockito.verify(animalRepository).save(animal);
    }

    @Test
    @DisplayName("Validação: Impedir mudança para cercados cheios")
    void preventMovementIntoCrowdedArea(){
        Long idAnimal = 1L;
        Long idSurrounded = 10L;

        Animal animal = Animal.builder().
                id(idAnimal)
                .status(AnimalStatus.ACTIVE)
                .build();
        AnimalDTO animalDTO = new AnimalDTO();

        Surrounded surroundedFull = createSurrounded(1L, 1);

        AnimalSurroundedMoveDTO moveAnimal = AnimalSurroundedMoveDTO.builder()
                .surrounded(idSurrounded)
                .build();

        Mockito.when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        Mockito.when(surroundedService.isExist(any())).thenReturn(surroundedFull);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            animalService.moveAnimal(idAnimal, moveAnimal);
        });

        System.out.print("Mensagem: "+ exception.getMessage()+ "\n");
        assertEquals("O piquete selecionado já atingiu a capacidade máxima", exception.getMessage());
        Mockito.verify(animalRepository, never()).save(any());

    }

    @Test
    @DisplayName("Validação: Impedir mudança de animais falecidos")
    void toPreventTheMovementOfDeceased(){
        Long idAnimal = 1L;
        Long idSurrounded = 10L;

        Animal animal = Animal.builder()
                .id(idAnimal)
                .status(AnimalStatus.DECEASED)
                .build();

        AnimalSurroundedMoveDTO moveAnimal = AnimalSurroundedMoveDTO.builder()
                .surrounded(idSurrounded)
                .build();

        Mockito.when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            animalService.moveAnimal(idAnimal, moveAnimal);
        });

        System.out.print("Mensagem: "+ exception.getMessage()+ "\n");
        assertEquals("Não é possível cadastrar, alocar ou alterar o status de animais falecidos.", exception.getMessage());
        Mockito.verify(animalRepository, never()).save(any());

    }


    @Test
    @DisplayName("Sucesso: Animal movido de piquete")
    void moveAnimal() {
        Long idAnimal = 1L;
        Long idSurrounded = 10L;

        Animal animal = Animal.builder().status(AnimalStatus.ACTIVE).build();
        Surrounded surrounded = createSurrounded(2L, 1);

        AnimalResponseDTO animalResponseDTO = AnimalResponseDTO.builder().build();
        AnimalSurroundedMoveDTO animaMove = AnimalSurroundedMoveDTO.builder().
                surrounded(idSurrounded)
                .build();

        Mockito.when(animalRepository.findById(idAnimal)).thenReturn(Optional.of(animal));
        Mockito.when(surroundedService.fetchEmptySurround(idSurrounded)).thenReturn(surrounded);

        Mockito.when(animalRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(mapper.toResponseDTO(animal)).thenReturn(animalResponseDTO);

        AnimalResponseDTO result = animalService.moveAnimal(idAnimal, animaMove);

        assertNotNull(result);
        assertEquals(surrounded.getId(), animal.getSurrounded().getId());
        Mockito.verify(animalRepository).save(animal);
    }

    @Test
    @DisplayName("Sucesso: Status Atualizado")
    void updateStatus() {
        Long idAnimal = 1L;
        Animal animal = Animal.builder().status(AnimalStatus.ACTIVE).build();
        AnimalResponseDTO animalResponseDTO = AnimalResponseDTO.builder().status(AnimalStatus.SOLD).build();

        UpdateAnimalStatusDTO updateStatus = new UpdateAnimalStatusDTO(AnimalStatus.SOLD);

        Mockito.when(animalRepository.findById(idAnimal)).thenReturn(Optional.of(animal));
        Mockito.when(animalRepository.save(any(Animal.class))).thenAnswer(i-> i.getArguments()[0]);
        Mockito.when(mapper.toResponseDTO(any(Animal.class))).thenReturn(animalResponseDTO);

        AnimalResponseDTO result = animalService.updateStatus(idAnimal, updateStatus);

        assertNotNull(result);
        assertEquals(updateStatus.status(), result.getStatus());
        Mockito.verify(animalRepository).save(animal);

    }

    @Test
    @DisplayName("Sucesso: Animal Deletado")
    void deleteAnimal() throws Exception {
        Long idAnimal = 1L;

        animalService.delete(idAnimal);
        verify(animalRepository, times(1)).deleteById(idAnimal);
    }

}