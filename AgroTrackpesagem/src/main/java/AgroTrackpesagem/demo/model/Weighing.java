package AgroTrackpesagem.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="weighing")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Weighing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="daily_weight", nullable = false)
    private double dailyWeightGain;

    @Column(name="weight", nullable = false)
    private double weight;

    @Column(name="weight_date", nullable = false, updatable = false)
    private LocalDate weightDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false, updatable = false)
    private Animal animal;
}
