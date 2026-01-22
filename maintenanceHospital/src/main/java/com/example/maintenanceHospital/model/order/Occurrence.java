package com.example.maintenanceHospital.model.order;

import com.example.maintenanceHospital.model.heritage.Heritage;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.people.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "occurrence")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Occurrence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "protocol", unique = true)
    private String protocol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "heritage_id", nullable = false)
    private Heritage heritage;

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name ="status", nullable = false, length = 20)
    private StatusOccurrence status;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Employee applicant;

    @Column(name = "sector", nullable = false, length = 30)
    private String sector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_service_id", nullable = true)
    @JsonBackReference // "Eu sou a volta do loop, não me serialize"
    private OrderService orderService;

    @PrePersist
    public void generateProtocol() {
        if (this.protocol == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss-S");
            this.protocol = LocalDateTime.now().format(formatter);
        }
    }
}
