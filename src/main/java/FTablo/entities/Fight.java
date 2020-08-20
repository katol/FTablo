package FTablo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fights")
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

    public Integer getSecondsPassed() {
        return secondsPassed;
    }

    public void setSecondsPassed(Integer secondsPassed) {
        this.secondsPassed = secondsPassed;
    }

    public Timestamp getEndTs() {
        return endTs;
    }

    public void setEndTs(Timestamp endTs) {
        this.endTs = endTs;
    }

    public String getEndDescription() {
        return endDescription;
    }

    public void setEndDescription(String endDescription) {
        this.endDescription = endDescription;
    }

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public Integer getRedScores() {
        return redScores;
    }

    public void setRedScores(Integer redScores) {
        this.redScores = redScores;
    }

    public String getBlueName() {
        return blueName;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    public Integer getBlueScores() {
        return blueScores;
    }

    public void setBlueScores(Integer blueScores) {
        this.blueScores = blueScores;
    }
}
