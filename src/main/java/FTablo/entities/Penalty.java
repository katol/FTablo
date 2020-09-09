package FTablo.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "penalties")
@Data
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_id")
    private Fight fight;

    @Column
    private Integer secondsPassed;

    @Column
    private Timestamp ts;

    @Column
    private String foulDescription;

    @Column
    private Color ruleBreakerColor;
}
