package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import AgroTrackpesagem.demo.enums.PaddockType;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalMapper;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalUpdateDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {
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
                .breed(Breeds.ANGUS)
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

    private Surrounded createSurrounded(int limit, int busy){
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
        List<AnimalResponseDTO> animalsResponse = List.of(new AnimalResponseDTO(), new AnimalResponseDTO());

        Mockito.when(animalRepository.findAll()).thenReturn(animals);
        Mockito.when(mapper.toResponseDTOList(animals)).thenReturn(animalsResponse);

        List<AnimalResponseDTO> result = animalService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(animalRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Sucesso: Animal encotrado")
    void findById() {
        AnimalResponseDTO responseMock = new AnimalResponseDTO();
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
    void shouldReturnErrorWhenStatusIsDeceased(){
        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.DECEASED);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            animalService.create(animalDTO);
        });

        System.out.print("Mensagem: "+ exception.getMessage() + "\n");

        assertEquals("Não é possivel cadastrar animals falecidos", exception.getMessage());
    }

    @Test
    @DisplayName("Validação: Impedir registro em cercados cheios")
    void shouldReturnErrorWhenSurroundedIsFull(){
        Surrounded surroundedFull = createSurrounded(1, 1);
        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.ACTIVE);
        Mockito.when(surroundedService.isExist(any())).thenReturn(surroundedFull);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            animalService.create(animalDTO);
        });

        System.out.print("Mensagem: "+ exception.getMessage()+ "\n");
        assertEquals("O piquete selecionado já atingiu a capacidade máxima", exception.getMessage());
    }

    @Test
    @DisplayName("Sucesso: Animal registrado")
    void create() {
        Surrounded surrounded = createSurrounded(2, 0);
        Animal animal = new Animal();

        AnimalDTO animalDTO = createAnimalDTO(AnimalStatus.ACTIVE);
        animalDTO.setSurrounded(surrounded.getId());

        Mockito.when(surroundedService.isExist(animalDTO.getId())).thenReturn(surrounded);

        Mockito.when(mapper.toEntity(animalDTO)).thenReturn(animal);
        Mockito.when(mapper.toResponseDTO(any())).thenReturn(new AnimalResponseDTO());
        Mockito.when(animalRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        AnimalResponseDTO result = animalService.create(animalDTO);

        assertNotNull(result);
        Mockito.verify(animalRepository).save(any());
    }

    @Test
    void update() {

    }

    @Test
    void moveAnimal() {
    }

    @Test
    void updateStatus() {
    }
}