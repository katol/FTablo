package FTablo.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exchanges")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_id")
    @NonNull
    private Fight fight;

    @Column
    @NonNull
    private Integer secondsPassed;

    @Column
    @NonNull
    private Timestamp saveTs;

    @Column
    @NonNull
    private String actionDescription;

    @Column
    @NonNull
    private Integer scoresToRed;

    @Column
    @NonNull
    private Integer scoresToBlue;
}
