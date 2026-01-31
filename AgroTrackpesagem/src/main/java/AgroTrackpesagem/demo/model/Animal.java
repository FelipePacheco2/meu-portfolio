package AgroTrackpesagem.demo.model;

import AgroTrackpesagem.demo.enums.AnimalStatus;
import AgroTrackpesagem.demo.enums.Breeds;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="animal")
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tag_identifier", nullable = false, unique = true, length = 50)
    private String tagIdentifier;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name="breed", nullable = false, length = 20)
    private Breeds breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="surrounded_id",nullable = false)
    private Surrounded surrounded;

    @OneToMany(mappedBy="animal", cascade = CascadeType.ALL)
    private List<Weighing> weights = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 20)
    private AnimalStatus status;
}
