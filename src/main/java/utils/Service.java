package utils;

import dao.PlanetDao;
import dao.SatelliteDao;
import dao.StarDao;
import entity.planet.Planet;
import entity.planet.PlanetComposition;
import entity.satellite.Satellite;
import entity.satellite.SatelliteForm;
import entity.star.Star;
import entity.star.StarType;
import org.hibernate.SessionFactory;

public class Service {

    public static void createCosmicBodies(SessionFactory sessionFactory) {
        Star star = new Star();
        star.setName("Sun");
        star.setStarType(StarType.YELLOW_DWARF);

        Satellite satellite = new Satellite();
        satellite.setName("Moon");
        satellite.setSatelliteForm(SatelliteForm.SPHEROID);

        Planet planet = new Planet();
        planet.setName("Earth");
        planet.setPlanetComposition(PlanetComposition.SOLID);

        PlanetDao planetDao = new PlanetDao(sessionFactory);
        SatelliteDao satelliteDao = new SatelliteDao(sessionFactory);
        StarDao starDao = new StarDao(sessionFactory);

        satelliteDao.save(satellite);
        planetDao.save(planet);
        starDao.save(star);
    }

    public static void updateCosmicBodiesConnections(SessionFactory sessionFactory) {
        PlanetDao planetDao = new PlanetDao(sessionFactory);
        SatelliteDao satelliteDao = new SatelliteDao(sessionFactory);
        StarDao starDao = new StarDao(sessionFactory);

        Planet planet = planetDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no planet with this id"));
        Satellite satellite = satelliteDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no satellite with this id"));
        Star star = starDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no star with this id"));

        planet.addSatellite(satellite);
        star.addPlanet(planet);

        satelliteDao.save(satellite);
        planetDao.save(planet);
        starDao.save(star);
    }

    public static void deleteCosmicBody(SessionFactory sessionFactory) {
        PlanetDao planetDao = new PlanetDao(sessionFactory);
        planetDao.delete(1L);
        System.out.println("Planet Earth was deleted, so were all its satellites");
    }

    public static void readCosmicBodies(SessionFactory sessionFactory) {
        PlanetDao planetDao = new PlanetDao(sessionFactory);
        SatelliteDao satelliteDao = new SatelliteDao(sessionFactory);
        StarDao starDao = new StarDao(sessionFactory);

        Planet planet = planetDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no planet with this id"));
        Satellite satellite = satelliteDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no satellite with this id"));;
        Star star = starDao.findById(1L)
                .orElseThrow(() -> new RuntimeException("There is no star with this id"));

        System.out.printf(satellite.toString());
        System.out.printf(planet.toString());
        System.out.printf(star.toString());
    }


}
