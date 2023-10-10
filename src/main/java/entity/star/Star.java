package entity.star;

import entity.BaseEntity;
import entity.planet.Planet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Star implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private StarType starType;

    @OneToMany(mappedBy = "star", cascade = CascadeType.ALL)
    private List<Planet> planetList = new ArrayList<>();

    @Override
    public String toString() {
        return new StringBuilder().append("Stars:").append("\n")
                .append("id: ").append(id).append("\n")
                .append("name: ").append(name).append("\n")
                .append("star type: ").append(starType).append("\n")
                .append("planets: ").append(checkPlanetRelation())
                .toString();
    }

    private List<String> checkPlanetRelation() {
        if(planetList == null) {
            return List.of("");
        }
        else {
            return planetList.stream()
                    .map(Planet::getName)
                    .toList();
        }
    }

    public void addPlanet(Planet planet) {
        planetList.add(planet);
        planet.setStar(this);
    }
}
