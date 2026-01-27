package com.example.maintenanceHospital.config;


import com.example.maintenanceHospital.model.people.RoleEmployee;
import com.example.maintenanceHospital.model.heritage.*;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.model.people.Employee;
import com.example.maintenanceHospital.repository.heritage.CategoryHeritageRepository;
import com.example.maintenanceHospital.repository.heritage.HeritageRepository;
import com.example.maintenanceHospital.repository.heritage.HeritageTypeRepository;
import com.example.maintenanceHospital.repository.heritage.LocationHospitalRepository;
import com.example.maintenanceHospital.repository.order.OccurrenceRepository;
import com.example.maintenanceHospital.repository.order.OrderServiceRepositoy;
import com.example.maintenanceHospital.repository.people.EmployeeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
public class TesteData implements CommandLineRunner {
    private final CategoryHeritageRepository cateHrep;
    private final LocationHospitalRepository locationRep;
    private final HeritageTypeRepository heritageTypeRep;
    private final HeritageRepository heritageRep;
    private final EmployeeRepository employeeRep;
    private final OccurrenceRepository occurrenceRep;
    private final OrderServiceRepositoy osRep;

    private final LocalDateTime data = LocalDateTime.now();


    public TesteData(CategoryHeritageRepository cateHrep,
        LocationHospitalRepository locationRep,
        HeritageTypeRepository heritageTypeRep,
        HeritageRepository heritageRep,
        EmployeeRepository employeeRep,
        OccurrenceRepository occurrenceRep,
        OrderServiceRepositoy osRep
    ){
        this.cateHrep = cateHrep;
        this.locationRep = locationRep;
        this.heritageTypeRep = heritageTypeRep;
        this.heritageRep = heritageRep;
        this.employeeRep = employeeRep;
        this.occurrenceRep = occurrenceRep;
        this.osRep =  osRep;
    }

    @Override
    public void run(String... args) throws Exception {

            if(cateHrep.count() == 0){
                // insert category
                CategoryHeritage cat1 = new CategoryHeritage();
                cat1.setName("Ventilação Pulmonar");
                cat1.setDescription("UTI Adulto/Pediátrico/Transporte e Emergência");
                cateHrep.save(cat1);

                // insert local
                LocationHospital local1 = new LocationHospital();
                local1.setName("Sala 1");
                locationRep.save(local1);

                //criar pessoa
                Employee employee1 = new Employee();
                employee1.setName("felipe");
                employee1.setRole(RoleEmployee.GESTOR_QUALIDADE);
                employeeRep.save(employee1);

                Employee employee2 = new Employee();
                employee2.setName("jorge");
                employee2.setRole(RoleEmployee.COORDENADOR_SLA);
                employeeRep.save(employee2);

                //criando o tipo do aparelho
                HeritageType heritageType1 = new HeritageType();
                heritageType1.setName("Servo-i / Bennett 980");
                heritageType1.setCategory(cat1);
                heritageType1.setCritically(CriticallyHeritage.EMERGENCIA);
                heritageType1.setDescription("Ventilador modular de alta performance, pioneiro na ventilação controlada neurologicamente (NAVA) para todos os perfis de pacientes.");
                heritageTypeRep.save(heritageType1);


                //cadastrando o aparelho
                Heritage heritage1 = new Heritage();
                heritage1.setTag("ss1");
                heritage1.setLocation(local1);
                heritage1.setTypeHeritage(heritageType1);
                heritage1.setSerialNumber("500123");
                heritage1.setDateBuy(data);
                heritage1.setWarrantyLimit(data);
                heritageRep.save(heritage1);


                Occurrence occu1 = new Occurrence();
                occu1.setHeritage(heritage1);
                occu1.setDescription("APARELHO NÂO LIGA E È ISSO");
                occu1.setApplicant(employee1);
                occu1.setSector("sala 1B");
                occu1.setStatus(StatusOccurrence.PENDENTE);

                //criar os
                OrderService os1 = new OrderService();
                os1.setTechnical(employee2);
                occu1.setOrderService(os1);

                os1.setPriority(CriticallyHeritage.EMERGENCIA);
                os1.setStatusService(StatusOccurrence.PENDENTE);

                List<Occurrence> listoccu1 = new ArrayList<>();
                listoccu1.add(occu1);
                os1.setOccurrences(listoccu1);
                osRep.save(os1);

                System.out.println(">>> Banco de dados populado com sucesso!");
            }else{
            System.out.println(">>> Banco já possui dados. Pulando jsons semeadura.");
        }
    }
}


