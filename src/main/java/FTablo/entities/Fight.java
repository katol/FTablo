package FTablo.entities;

import FTablo.Color;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fights")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer secondsPassed = 0;

    @Column
    private Integer lastExchangeNumber = 0;

    @Column
    private Timestamp lastTs;

    @Column
    private String lastDescription;

    @Column
    @NonNull
    private String redName;

    @Column
    private Integer redScores = 0;

    @Column
    private Integer redPenalties = 0;

    @Column
    private Integer redVideoReplays = 0;

    @Column
    @NonNull
    private String blueName;

    @Column
    private Integer blueScores = 0;

    @Column
    private Integer bluePenalties = 0;

    @Column
    private Integer blueVideoReplays = 0;

    public void addExchange(Exchange exchange) {
        secondsPassed = exchange.getSecondsPassed();
        lastExchangeNumber++;
        lastTs = exchange.getSaveTs();
        lastDescription = exchange.getActionDescription();
        redScores += exchange.getScoresToRed();
        blueScores += exchange.getScoresToBlue();
    }

    public void addPenalty(Penalty penalty) {
        secondsPassed = penalty.getSecondsPassed();
        lastTs = penalty.getTs();
        lastDescription = penalty.getFoulDescription();

        if (penalty.getRuleBreakerColor().equals(Color.RED)) {
            redPenalties++;
        } else if (penalty.getRuleBreakerColor().equals(Color.BLUE)) {
            bluePenalties++;
        }
    }
}
