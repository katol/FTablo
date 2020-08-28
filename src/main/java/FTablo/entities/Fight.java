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
    private Timestamp endTs;

    @Column
    private String endDescription;

    @Column
    private String redName;

    @Column
    private Integer redScores;

    @Column
    private String blueName;

    @Column
    private Integer blueScores;
}
