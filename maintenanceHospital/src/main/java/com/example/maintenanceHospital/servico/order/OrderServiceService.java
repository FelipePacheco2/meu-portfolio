package com.example.maintenanceHospital.servico.order;

import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceMapper;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceMapper;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.repository.order.OrderServiceRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
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
    OccurrenceService serviceOcc;

    public List<OrderServiceDTO> findAll(){
        List<OrderServiceDTO> orderServicos = mapper.toDTOList(repository.findAll());
        List<Occurrence> occurrences = new ArrayList<>();

        for(OrderServiceDTO item : orderServicos){
            for(Occurrence item2 : item.occurrences()){
                Occurrence occurrence = serviceOcc.findById(item2.getId());
                occurrences.add(occurrence);
            } //map leftJoin @query
        }


        return mapper.toDTOList(repository.findAll());
    }

    public OrderServiceDTO create(OrderServiceDTO dto) {
        OrderService entity = mapper.toEntity(dto);
        List<Occurrence> occurrences = new ArrayList<>();

        for(Occurrence item : dto.occurrences()){
            Occurrence oco = serviceOcc.findById(item.getId());
            occurrences.add(oco);
            entity.setOccurrences(occurrences);
        }

        OrderService savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }
}
