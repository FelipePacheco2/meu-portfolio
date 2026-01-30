package AgroTrackpesagem.demo.service;

import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.model.Surrounded;
import AgroTrackpesagem.demo.repository.SurroundedRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurroundedService {
    private final SurroundedRepository repository;
    private final SurroundedMapper mapper;

    public List<SurroundedResponseDTO> listAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public SurroundedResponseDTO findById(Long id){
        return mapper.toResponseDTO(isExist(id));
    }


    public Surrounded isExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("piquete não encontrado com ID: " + id));
    }
}
