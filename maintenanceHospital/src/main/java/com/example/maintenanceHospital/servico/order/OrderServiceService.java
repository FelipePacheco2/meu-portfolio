package com.example.maintenanceHospital.servico.order;

import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceMapper;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.repository.order.OrderServiceRepositoy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return mapper.toDTOList(repository.findAllFull());
    }

    public OrderServiceDTO findById(Long id){
        OrderService os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));
        return mapper.toDTO(os);
    }

    @Transactional
    public OrderServiceDTO create(OrderServiceDTO dto) {
        OrderService entity = mapper.toEntity(dto);

        //Separa os IDs das ocorrências
        List<Long> occurrenceIds = entity.getOccurrences().stream()
                .map(Occurrence::getId)
                .toList();

            if(occurrenceService.hasPendingOccurrences(occurrenceIds)){
                entity.setOccurrences(new ArrayList<>());
                OrderService saveEntity = repository.save(entity);

                List<Occurrence> approvedOccurrences = occurrenceService.approveAndLink(occurrenceIds, saveEntity);
                saveEntity.setOccurrences(approvedOccurrences);

                repository.save(saveEntity);
                entityManager.clear();
                return mapper.toDTO(repository.findByIdObject(saveEntity.getId()));

            }

        throw new RuntimeException("Erro ao criar OS: Ocorrências inexistentes ou já vinculadas jsons outra OS. Operação cancelada.");
    }

    @Transactional
    public OrderServiceDTO update(Long id, OrderServiceDTO dto){
        OrderService entity = mapper.toEntity(findById(dto.id()));
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

}
