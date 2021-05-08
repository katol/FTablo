package FTablo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fights")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(value = {"exchanges"})
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    @NonNull
    private Integer secondsPassed = 0;

    @Column
    @NonNull
    private Integer lastExchangeNumber = 0;

    @Column
    @NonNull
    private Timestamp lastTs = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MICROS));

    @Column
    @NonNull
    private String lastDescription = "";

    @Column
    @NonNull
    @NotEmpty(message = "Name of red fighter should not be empty")
    private String redName;

    @Column
    @NonNull
    private Integer redScores = 0;

    @Column
    @NonNull
    private Integer redPenalties = 0;

    @Column
    @NonNull
    private Integer redVideoReplays = 0;

    @Column
    @NonNull
    @NotEmpty(message = "Name of blue fighter should not be empty")
    private String blueName;

    @Column
    @NonNull
    private Integer blueScores = 0;

    @Column
    @NonNull
    private Integer bluePenalties = 0;

    @Column
    @NonNull
    private Integer blueVideoReplays = 0;

    @OneToMany(mappedBy = "fight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exchange> exchanges = new ArrayList<>();

    public void addExchange(Exchange exchange) {
        exchanges.add(exchange);
        exchange.setFight(this);
        secondsPassed = exchange.getSecondsPassed();
        lastExchangeNumber++;
        lastTs = exchange.getSaveTs();
        lastDescription = exchange.getActionDescription();
        redScores += exchange.getScoresToRed();
        redPenalties += exchange.getPenaltiesToRed();
        blueScores += exchange.getScoresToBlue();
        bluePenalties += exchange.getPenaltiesToBlue();
    }
}
