package FTablo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "exchanges")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@JsonIgnoreProperties(value = {"fight"})
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fight_id")
    @NonNull
    private Fight fight;

    @Column
    @NonNull
    @NotNull(message = "Count of passed seconds should not be null")
    private Integer secondsPassed;

    @Column
    @NonNull
    @NotNull(message = "Timestamp of save should not be null")
    private Timestamp saveTs;

    @Column
    @NonNull
    private String actionDescription = "";

    @Column
    @NonNull
    private Integer scoresToRed = 0;

    @Column
    @NonNull
    private Integer penaltiesToRed = 0;

    @Column
    @NonNull
    private Integer scoresToBlue = 0;

    @Column
    @NonNull
    private Integer penaltiesToBlue = 0;
}
