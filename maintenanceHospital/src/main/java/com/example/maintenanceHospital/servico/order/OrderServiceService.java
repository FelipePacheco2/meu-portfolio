package com.example.maintenanceHospital.servico.order;

import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceMapper;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceMapper;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.repository.order.OccurrenceRepository;
import com.example.maintenanceHospital.repository.order.OrderServiceRepositoy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceService {

    @Autowired
    OrderServiceRepositoy repository;
    @Autowired
    OrderServiceMapper mapper;
    @Autowired
    OccurrenceService occurrenceService;
    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderServiceDTO> findAll(){
        return mapper.toDTOList(repository.findAllCompleto());
    }

    @Transactional
    public OrderServiceDTO create(OrderServiceDTO dto) {
        OrderService entity = mapper.toEntity(dto);

        //Separa os IDs das ocorrências
        List<Long> occurrenceIds = entity.getOccurrences().stream()
                .map(Occurrence::getId)
                .toList();
        List<Occurrence> approvedOccurrences = occurrenceService.approveAndLink(occurrenceIds, saveEntity);


        entity.setOccurrences(new ArrayList<>());
        OrderService saveEntity = repository.save(entity);

        List<Occurrence> approvedOccurrences = occurrenceService.approveAndLink(occurrenceIds, saveEntity);

        if (approvedOccurrences == null || approvedOccurrences.isEmpty()) {
            throw new RuntimeException("Falha ao criar OS: Nenhuma ocorrência disponível. O registro foi cancelado.");
            return null;
        }

        saveEntity.setOccurrences(approvedOccurrences);
        repository.save(saveEntity);
        entityManager.clear();

        return mapper.toDTO(repository.findByIdObject(saveEntity.getId()));
    }
}
