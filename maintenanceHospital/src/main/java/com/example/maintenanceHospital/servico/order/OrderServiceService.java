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
        entity.setOccurrences(new ArrayList<>());
        OrderService saveEntity = repository.save(entity);

        List<Occurrence> approvedOccurrences = occurrenceService.approveAndLink(occurrenceIds, saveEntity);
        saveEntity.setOccurrences(approvedOccurrences);

        return mapper.toDTO(repository.save(entity));
    }
}
