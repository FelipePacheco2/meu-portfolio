package com.felipe.tableOrder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "MenuItem")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idItemMenu")

public class MenuItemID {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemMenu;
    @Column(name="name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name="description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name="type", nullable = false, columnDefinition = "TEXT")
    private String type;
    @Column(name="sale", nullable = false, precision = 5, scale = 2)
    @Positive()
    private BigDecimal sale;

}
