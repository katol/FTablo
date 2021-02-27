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
    private Integer lastExchangeNumber;

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
    private Integer redPenalties;

    @Column
    private Integer redVideoReplays;

    @Column
    @NonNull
    private String blueName;

    @Column
    private Integer blueScores = 0;

    @Column
    private Integer bluePenalties;

    @Column
    private Integer blueVideoReplays;

    public void addExchange(Exchange exchange) {
        secondsPassed = exchange.getSecondsPassed();
        lastExchangeNumber = 1;
        lastTs = exchange.getSaveTs();
        lastDescription = exchange.getActionDescription();
        if (exchange.getScoresToRed() != null) {
            System.out.println(exchange.getScoresToRed());
            redScores = exchange.getScoresToRed();
        }else {
            redScores = 0;
        }
        if (exchange.getScoresToBlue() != null) {
            blueScores = exchange.getScoresToBlue();
        } else {
            blueScores = 0;
        }
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
