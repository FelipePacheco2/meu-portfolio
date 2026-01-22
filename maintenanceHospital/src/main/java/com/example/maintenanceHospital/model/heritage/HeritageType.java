package com.example.maintenanceHospital.model.heritage;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="heritage_type")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HeritageType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //
    @JoinColumn(name = "category", nullable = false)
    private CategoryHeritage category;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private CriticallyHeritage critically;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
}
