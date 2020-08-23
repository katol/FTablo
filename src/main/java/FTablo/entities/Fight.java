package FTablo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fight fight = (Fight) o;
        return id.equals(fight.id) &&
                secondsPassed.equals(fight.secondsPassed) &&
                endTs.equals(fight.endTs) &&
                endDescription.equals(fight.endDescription) &&
                redName.equals(fight.redName) &&
                redScores.equals(fight.redScores) &&
                blueName.equals(fight.blueName) &&
                blueScores.equals(fight.blueScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secondsPassed, endTs, endDescription, redName, redScores, blueName, blueScores);
    }

    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", secondsPassed=" + secondsPassed +
                ", endTs=" + endTs +
                ", endDescription='" + endDescription + '\'' +
                ", redName='" + redName + '\'' +
                ", redScores=" + redScores +
                ", blueName='" + blueName + '\'' +
                ", blueScores=" + blueScores +
                '}';
    }

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
