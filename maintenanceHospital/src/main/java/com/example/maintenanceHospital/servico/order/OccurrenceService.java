package com.example.maintenanceHospital.servico.order;

import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceMapper;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.repository.order.OccurrenceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OccurrenceService {
    @Autowired
    OccurrenceRepository repository;
    @Autowired
    OccurrenceMapper mapper;
    @PersistenceContext
    EntityManager entityManager;

    public List<OccurrenceDTO> findALl(){
        return mapper.toDTOList(repository.findAllFull());
    }

    @Transactional
    public OccurrenceDTO create(OccurrenceDTO dto){
        Occurrence entity =  repository.save(mapper.toEntity(dto));
        entityManager.clear();
        return mapper.toDTO(repository.findByIdObject(entity.getId()));
    }

    @Transactional
    public OccurrenceDTO update(OccurrenceDTO dto){
        Occurrence entity = findById(dto.id());
        mapper.updateEntityFromDTO(dto, entity);
        repository.save(entity);
        return mapper.toDTO(findById(entity.getId()));
    }


    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }


    public Occurrence findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    public Occurrence findByID(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    // retorna list de ocorrencia aprovada para uma order
    public List<Occurrence> approveAndLink(List<Long> occurrenceIds, OrderService order){
        if (occurrenceIds == null || occurrenceIds.isEmpty()) {
            return new ArrayList<>();
        }

        // Executa o UPDATE em massa
        int updatedRows = repository.updateStatusAndLinkOrder(
                occurrenceIds,
                order,
                StatusOccurrence.APROVADA,
                StatusOccurrence.PENDENTE
        );

        if (updatedRows < occurrenceIds.size()) {
            // lança exceção ou apenas loga um aviso
            System.out.println("Aviso: Algumas ocorrências já estavam vinculadas ou não eram pendentes.");
        }

        // Busca as ocorrências atualizadas para retornar ao OrderService
        return repository.findAllById(occurrenceIds);
    }

    public
}
