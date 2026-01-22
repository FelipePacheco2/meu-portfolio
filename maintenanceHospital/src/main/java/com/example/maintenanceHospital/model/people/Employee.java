package com.example.maintenanceHospital.model.people;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
}
