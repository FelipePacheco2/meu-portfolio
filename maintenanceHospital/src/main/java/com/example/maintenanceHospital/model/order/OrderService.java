package com.example.maintenanceHospital.model.order;

import com.example.maintenanceHospital.model.heritage.CriticallyHeritage;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.people.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderService")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class OrderService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "protocol", unique = true)
    private String protocol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technicalId")
    private Employee technical;

    @OneToMany(mappedBy = "orderService", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonManagedReference // "Eu gerencio, pode serializar meus filhos"
    private List<Occurrence> occurrences = new ArrayList<>();

    @Column(name = "endDate", nullable = false, updatable = false)
    private LocalDateTime endDate;

    @Column(name = "closingDate", nullable = true)
    private LocalDateTime closingDate;

    @CreationTimestamp
    @Column(name = "openingDate")
    private LocalDateTime openingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 12)
    private CriticallyHeritage priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusService", nullable = false, length = 20)
    private StatusOccurrence statusService;

    @Column(name = "description", nullable = true)
    private String description;


    @PrePersist
    public void generateProtocol() {
        if (this.openingDate == null) {
            this.openingDate = LocalDateTime.now();
        }
        this.endDate = switch (this.priority) {
            case EMERGENCIA -> openingDate.plusHours(2);
            case ALTA       -> openingDate.plusHours(4);
            case MEDIA      -> openingDate.plusHours(8);
            case BAIXA      -> openingDate.plusHours(24);
            default         -> openingDate.plusHours(24);
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss-S");
        this.protocol = LocalDateTime.now().format(formatter);
    }

}
