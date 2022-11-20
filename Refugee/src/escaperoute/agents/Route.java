package escaperoute.agents;

import java.util.Random;

import org.geotools.geometry.DirectPosition2D;
import org.opengis.geometry.DirectPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.Network;
import repast.simphony.util.ContextUtils;


public class Route {

	private Point currentLocation = null;
	private GeometryFactory fac = new GeometryFactory();
	public static int StartNumber = 0;
	
	public Point Start() {
		// erzeugt eine zufällige Zahl zwischen 0 und 3, hiermit wird eine der vier Routen gewählt
		Random random = new Random();
		int WhichRoute = random.nextInt(4);
		
		if (WhichRoute == 0) {
			//Chios
			Point geom = fac.createPoint(new Coordinate(26.10, 38.24));
			currentLocation = geom;	
			StartNumber = 1;
		} else if (WhichRoute == 1) {
			//Samos
			Point geom = fac.createPoint(new Coordinate(26.58, 37.45));
			currentLocation = geom;	
			StartNumber = 2;
		} else if (WhichRoute == 2) {
			//Kos
			Point geom = fac.createPoint(new Coordinate(27.70, 36.49));
			currentLocation = geom;
			StartNumber = 3;
		} else if (WhichRoute == 3) {
			//Istanbul
			Point geom = fac.createPoint(new Coordinate(28.58, 41.10));
			currentLocation = geom;
			StartNumber = 4;
		}
		return currentLocation;
	}
	
	public Point BalkanRoute(int i) {
		
		//Athen
		if (i == 0) {
			Point geom = fac.createPoint(new Coordinate(23.44, 37.59));
			currentLocation = geom;	
		}
		//Lamia
		if (i == 1) {
			Point geom = fac.createPoint(new Coordinate(22.26, 38.54));
			currentLocation = geom;	
		}
		//Kozani
		if (i == 2) {
			Point geom = fac.createPoint(new Coordinate(21.48, 40.19));
			currentLocation = geom;	
		}
		//Thessaloniki
		if (i == 3) {
			Point geom = fac.createPoint(new Coordinate(22.58, 40.39));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Bogorodica
		if (i == 4) {
			Point geom = fac.createPoint(new Coordinate(22.55, 41.13));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Tabanovce
		if (i == 5) {
			Point geom = fac.createPoint(new Coordinate(21.70, 42.24));
			currentLocation = geom;	
		}
		//Vlase
		if (i == 6) {
			Point geom = fac.createPoint(new Coordinate(21.87, 42.69));
			currentLocation = geom;	
		}
		//Nis
		if (i == 7) {
			Point geom = fac.createPoint(new Coordinate(21.54, 43.19));
			currentLocation = geom;	
		}
		//Belgrad
		if (i == 8) {
			Point geom = fac.createPoint(new Coordinate(20.28, 44.49));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Batrovci
		if (i == 9) {
			Point geom = fac.createPoint(new Coordinate(19.10, 45.05));
			currentLocation = geom;	
		}
		//Zagreb
		if (i == 10) {
			Point geom = fac.createPoint(new Coordinate(15.58, 45.48));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Bregana
		if (i == 11) {
			Point geom = fac.createPoint(new Coordinate(15.69, 45.85));
			currentLocation = geom;	
		}
		//Krsko
		if (i == 12) {
			Point geom = fac.createPoint(new Coordinate(15.28, 45.58));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Radlpass
		if (i == 13) {
			Point geom = fac.createPoint(new Coordinate(15.21, 46.65));
			currentLocation = geom;	
		}
		//Klagenfurt am Wörtersee
		if (i == 14) {
			Point geom = fac.createPoint(new Coordinate(14.31, 46.63));
			currentLocation = geom;	
		}
		//Mittersill
		if (i == 15) {
			Point geom = fac.createPoint(new Coordinate(12.28, 47.16));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Achenpass
		if (i == 16) {
			Point geom = fac.createPoint(new Coordinate(11.63, 47.59));
			currentLocation = geom;	
		}
		//Berlin
		if (i == 17) {
			Point geom = fac.createPoint(new Coordinate(13.24, 52.31));
			currentLocation = geom;	
		}
		return currentLocation;
	}
	
	public Point SouthRoute(int i) {
		//Startpunkt Athen
		if (i == 0) {
			Point geom = fac.createPoint(new Coordinate(23.44, 37.59));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Dogana Drimadhe
		if (i == 1) {
			Point geom = fac.createPoint(new Coordinate(20.43, 40.07));
			currentLocation = geom;	
		}
		//Vlora
		if (i == 2) {
			Point geom = fac.createPoint(new Coordinate(19.48, 40.45));
			currentLocation = geom;	
		}
		//Shkodra
		if (i == 3) {
			Point geom = fac.createPoint(new Coordinate(19.31, 42.40));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Hani i Hoti
		if (i == 4) {
			Point geom = fac.createPoint(new Coordinate(19.42, 42.33));
			currentLocation = geom;	
		}
		//Podgorica
		if (i == 5) {
			Point geom = fac.createPoint(new Coordinate(19.16, 42.26));
			currentLocation = geom;	
		}
		//Kotor
		if (i == 6) {
			Point geom = fac.createPoint(new Coordinate(18.77, 42.43));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Sitnica
		if (i == 7) {
			Point geom = fac.createPoint(new Coordinate(18.44, 42.56));
			currentLocation = geom;	
		}
		//Trebinje
		if (i == 8) {
			Point geom = fac.createPoint(new Coordinate(18.19, 42.43));
			currentLocation = geom;	
		}
		//Ravno
		if (i == 9) {
			Point geom = fac.createPoint(new Coordinate(17.58, 42.53));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Metkovic
		if (i == 10) {
			Point geom = fac.createPoint(new Coordinate(17.66, 43.05));
			currentLocation = geom;	
		}
		//Skradin
		if (i == 11) {
			Point geom = fac.createPoint(new Coordinate(15.55, 43.49));
			currentLocation = geom;	
		}
		//Rijeka
		if (i == 12) {
			Point geom = fac.createPoint(new Coordinate(14.25, 45.19));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Rupa
		if (i == 13) {
			Point geom = fac.createPoint(new Coordinate(14.28, 45.49));
			currentLocation = geom;	
		}
		//Sezana
		if (i == 14) {
			Point geom = fac.createPoint(new Coordinate(13.86, 45.71));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Karawankentunnel
		if (i == 15) {
			Point geom = fac.createPoint(new Coordinate(14.01, 46.48));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Suben/Neuhaus am Inn
		if (i == 16) {
			Point geom = fac.createPoint(new Coordinate(13.43, 48.41));
			currentLocation = geom;	
		}
		//Berlin
		if (i == 17) {
			Point geom = fac.createPoint(new Coordinate(13.24, 52.31));
			currentLocation = geom;	
		}
		return currentLocation;
	}
	
	public Point NorthRoute1(int i) {
		//Startpunkt Istanbul
		if (i == 0) {
			Point geom = fac.createPoint(new Coordinate(28.58, 41.10));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Kapitan AnKapitan Andreewo-Kapikule
		if (i == 1) {
			Point geom = fac.createPoint(new Coordinate(26.35, 41.72));
			currentLocation = geom;
		}
		//Sofia
		if (i == 2) {
			Point geom = fac.createPoint(new Coordinate(23.19, 42.42));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Kalotina
		if (i == 3) {
			Point geom = fac.createPoint(new Coordinate(22.83, 43.00));
			currentLocation = geom;
		}
		//Nis
		if (i == 4) {
			Point geom = fac.createPoint(new Coordinate(21.54, 43.19));
			currentLocation = geom;
		}
		return currentLocation;
	}
	
	public Point NorthRoute2(int i) {
		//Startpunkt Sofia
		if (i == 0) {
			Point geom = fac.createPoint(new Coordinate(23.19, 42.42));
			currentLocation = geom;
		}
		//Botewgrad
		if (i == 1) {
			Point geom = fac.createPoint(new Coordinate(23.47, 42.54));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Podul Calafat-Vidin
		if (i == 2) {
			Point geom = fac.createPoint(new Coordinate(22.95, 44.00));
			currentLocation = geom;
		}
		//Craiova
		if (i == 3) {
			Point geom = fac.createPoint(new Coordinate(23.48, 44.19));
			currentLocation = geom;
		}
		//Drobeta Turnu Severin
		if (i == 4) {
			Point geom = fac.createPoint(new Coordinate(22.40, 44.38));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Nadlac (1 und 2)
		if (i == 5) {
			Point geom = fac.createPoint(new Coordinate(20.73, 46.20));
			currentLocation = geom;
		}
		//Szeged
		if (i == 6) {
			Point geom = fac.createPoint(new Coordinate(20.90, 46.15));
			currentLocation = geom;
		}
		//Veszprém
		if (i == 7) {
			Point geom = fac.createPoint(new Coordinate(17.55, 47.60));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Heiligenkreuz im Lafnitztal
		if (i == 8) {
			Point geom = fac.createPoint(new Coordinate(16.28, 46.98));
			currentLocation = geom;
		}
		//Graz
		if (i == 9) {
			Point geom = fac.createPoint(new Coordinate(15.26, 47.40));
			currentLocation = geom;
		}
		//Liezen
		if (i == 10) {
			Point geom = fac.createPoint(new Coordinate(14.14, 47.34));
			currentLocation = geom;
		}
		//Grenzübergangsstelle Bad Reichenhall
		if (i == 11) {
			Point geom = fac.createPoint(new Coordinate(12.94, 47.77));
			currentLocation = geom;
		}
		//Berlin
		if (i == 12) {
			Point geom = fac.createPoint(new Coordinate(13.24, 52.31));
			currentLocation = geom;
		}
		return currentLocation;
	}
	
	public Point AlternativeRoute(int i) {
		//Startpunkt Athen
		if (i == 0) {
			Point geom = fac.createPoint(new Coordinate(23.44, 37.59));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Kristallopigi - Bilisht
		if (i == 1) {
			Point geom = fac.createPoint(new Coordinate(21.06, 40.62));
			currentLocation = geom;	
		}
		//Tirana
		if (i == 2) {
			Point geom = fac.createPoint(new Coordinate(19.49, 41.20));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Morine - Vermice
		if (i == 3) {
			Point geom = fac.createPoint(new Coordinate(20.55, 42.16));
			currentLocation = geom;	
		}
		//Grenzübergangsstelle Merdare
		if (i == 4) {
			Point geom = fac.createPoint(new Coordinate(21.24, 42.94));
			currentLocation = geom;	
		}
		//Nis
		if (i == 5) {
			Point geom = fac.createPoint(new Coordinate(21.54, 43.19));
			currentLocation = geom;	
		}
		return currentLocation;
	}
}

