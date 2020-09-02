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
}
