package refugee.agents;

import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.graph.RepastEdge;
import repast.simphony.util.ContextUtils;

/**
 * Routes, which Refugees can use.
 * 
 * @author Marie Genuit
 *
 */
public class Route {

	//die klassische Balkanroute in 2015
	static  Coordinate getBalkanRoute(Integer i) {
		
		GeometryFactory fac = new GeometryFactory();

		//Start - Koordinaten für Chios
		if (i == 0) {
			Coordinate coord = new Coordinate(26, 38);
			return coord;
		}
		// Koordinaten für Sithonia (Chalkidiki)
		if (i == 1) {
			Coordinate coord = new Coordinate(23, 40);
			return coord;
		}

		return null;
	}
}

