package AgroTrackpesagem.demo.model;

import AgroTrackpesagem.demo.enums.PaddockType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="surrounded")
@Getter @Setter
@EqualsAndHashCode @Builder
@NoArgsConstructor @AllArgsConstructor
public class Surrounded {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private PaddockType type;

    @Column(name="name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name="max_capacity", nullable = false)
    private Long maxCapacity;

    @CreationTimestamp
    @Column(name="create_date", updatable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "surrounded", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Animal> animals = new ArrayList<>();

}
