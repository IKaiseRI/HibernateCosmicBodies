package entity.satellite;

import entity.BaseEntity;
import entity.planet.Planet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Satellite implements BaseEntity <Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private SatelliteForm satelliteForm;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;

    @Override
    public String toString() {
        return new StringBuilder().append("Satellites:").append("\n")
                .append("id: ").append(id).append("\n")
                .append("name: ").append(name).append("\n")
                .append("satellite form: ").append(satelliteForm).append("\n")
                .append("planet: ").append(checkPlanetRelation()).append("\n")
                .toString();
    }

    public String checkPlanetRelation() {
        if(planet == null) {
            return "";
        }
        else return planet.getName();
    }
}
