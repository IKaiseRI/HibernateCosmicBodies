package entity.planet;

import entity.BaseEntity;
import entity.satellite.Satellite;
import entity.star.Star;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Planet implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "planet_composition")
    private PlanetComposition planetComposition;

    @ManyToOne
    @JoinColumn(name = "star_id")
    private Star star;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL)
    private List<Satellite> satelliteList = new ArrayList<>();

    @Override
    public String toString() {
        return new StringBuilder().append("Planets:").append("\n")
                .append("id: ").append(id).append("\n")
                .append("name: ").append(name).append("\n")
                .append("planet composition: ").append(planetComposition).append("\n")
                .append("star: ").append(checkStarRelation() ).append("\n")
                .append("satellites: ").append(checkSatellitesRelation()).append("\n")
                .toString();
    }

    private List<String> checkSatellitesRelation() {
        if(satelliteList == null) {
            return List.of("");
        }
        else {
            return satelliteList.stream()
                    .map(Satellite::getName)
                    .toList();
        }
    }

    public String checkStarRelation() {
        if(star == null) {
            return "";
        }
        else return star.getName();
    }

    public void addSatellite(Satellite satellite) {
        satelliteList.add(satellite);
        satellite.setPlanet(this);
    }
}
