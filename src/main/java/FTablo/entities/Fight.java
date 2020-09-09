package FTablo.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fights")
@Data
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer secondsPassed;

    @Column
    private Integer lastExchangeNumber;

    @Column
    private Timestamp lastTs;

    @Column
    private String lastDescription;

    @Column
    private String redName;

    @Column
    private Integer redScores;

    @Column
    private Integer redPenalties;

    @Column
    private Integer redVideoReplays;

    @Column
    private String blueName;

    @Column
    private Integer blueScores;

    @Column
    private Integer bluePenalties;

    @Column
    private Integer blueVideoReplays;

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
