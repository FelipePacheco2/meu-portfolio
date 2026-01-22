package com.example.maintenanceHospital.model.heritage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="heritage")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Heritage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="location", nullable = false)
    private LocationHospital location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="type_Heritage", nullable = false)
    private HeritageType typeHeritage;

    @Column(length = 20)
    private String serialNumber;

    @Column(nullable = true)
    private LocalDateTime dateBuy;

    @Column(nullable = true)
    private LocalDateTime warrantyLimit;

}
