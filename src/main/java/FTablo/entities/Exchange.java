package FTablo.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exchanges")
@Data
public class Exchange {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_id")
    private Fight fight;

    @Column
    private Integer secondsPassed;

    @Column
    private Timestamp saveTs;

    @Column
    private String actionDescription;

    @Column
    private Integer scoresToRed;

    @Column
    private Integer scoresToBlue;
}
