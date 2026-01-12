package com.felipe.tableOrder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idOrder")

public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @Column(name="numberTable", nullable = false)
    @Positive()
    private int numberTable;
    @CreationTimestamp
    @Column(name = "DateOrder", updatable = false)
    private LocalDateTime DateOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemOrder> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="idPayment")
    @JsonIgnore
    private PaymentRegister payment;

    public void addItems(ItemOrder item){
        items.add(item);
        item.setOrder(this);
    }

}
